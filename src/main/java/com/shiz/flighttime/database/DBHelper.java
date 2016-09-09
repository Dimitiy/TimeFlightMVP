package com.shiz.flighttime.database;

import android.content.Context;
import android.util.Log;

import com.shiz.flighttime.MyApplication;
import com.shiz.flighttime.R;
import com.shiz.flighttime.data.YearEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

import static io.realm.Realm.Transaction;

/**
 * Created by OldMan on 14.02.2016.
 */
public class DBHelper implements DBInterface {
    private Realm realm;
    private String TAG = "DBHelper";
    private RealmAsyncTask transaction;
    private Context context;

    public DBHelper() {
        realm = Realm.getDefaultInstance();
        this.context = MyApplication.getContext();
    }

    public void onStop() {
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }


    public void addListener(RealmChangeListener listener) {
        if (!realm.isClosed())
            realm.addChangeListener(listener);
    }

    public void deleteListener(RealmChangeListener listener) {
        if (!realm.isClosed())
            realm.removeChangeListener(listener);
    }

    @Override
    public void insertMission(final String city, final Calendar date) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MissionDB mission = realm.createObject(MissionDB.class);
                mission.setId(getPrimaryKey(mission));
                mission.setCity(city);
                mission.setDate(date.getTime());
                Log.d(TAG, "insertMission");
            }
        });
    }

    @Override
    public void insertFlightInMission(final int id, final Calendar date, final long duration, final boolean isDay) {
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                FlightDB flight = realm.createObject(FlightDB.class);
                flight.setId(getPrimaryKey(flight));
                flight.setDate(date.getTime());
                flight.setDuration(duration);
                flight.setDay(isDay);

                MissionDB mission = realm.where(MissionDB.class)
                        .equalTo("id", id)
                        .findFirst();
                mission.getFlightDBRealmList().add(flight);
                mission.setDuration(mission.getDuration() + duration);
                Log.d(TAG, "mission" + mission.getCity());

                if (flight.isDay())
                    mission.setDayDuration(mission.getDayDuration() + flight.getDuration());
                else
                    mission.setNightDuration(mission.getNightDuration() + flight.getDuration());
            }
        });
    }

    @Override
    public void insertFlightInMission(final int id, final FlightDB flight) {
        realm.executeTransactionAsync(new Transaction() {
            @Override
            public void execute(Realm realm) {
                MissionDB mission = realm.where(MissionDB.class)
                        .equalTo("id", id)
                        .findFirst();
                mission.getFlightDBRealmList().add(flight);
                mission.setDuration(mission.getDuration() + flight.getDuration());
                if (flight.isDay())
                    mission.setDuration(mission.getDayDuration() + flight.getDuration());
                else
                    mission.setDuration(mission.getNightDuration() + flight.getDuration());
            }
        });
    }

    @Override
    public void deleteMission(final int id) {
        realm.executeTransactionAsync(new Transaction() {
            @Override
            public void execute(Realm realm) {
                MissionDB results = realm.where(MissionDB.class).equalTo("id", id).findFirst();
                results.deleteFromRealm();
            }
        });
    }

    @Override
    public void deleteFlightInMission(final int id_mission, final int id_flight) {
        realm.executeTransactionAsync(new Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.d("DBHelper", "mission" + id_mission + "flight" + id_flight);
                MissionDB mission = realm.where(MissionDB.class).equalTo("id", id_mission).findFirst();
                FlightDB flight = mission.getFlightDBRealmList().where().equalTo("id", id_flight).findFirst();
                mission.setDuration(mission.getDuration() - flight.getDuration());
                if (flight.isDay())
                    mission.setDuration(mission.getDayDuration() - flight.getDuration());
                else
                    mission.setDuration(mission.getNightDuration() + flight.getDuration());
                flight.deleteFromRealm();
            }
        });
    }

    @Override
    public void updateFlightInMission(final int id_mission, final int id_flight, final Date date, final long duration, final boolean isDay) {
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                MissionDB mission = realm.where(MissionDB.class).equalTo("id", id_mission).findFirst();
                FlightDB flight = mission.getFlightDBRealmList().where().equalTo("id", id_flight).findFirst();
                Log.d("DBHelper", "flight");

                if (flight != null) {
                    mission.setDuration(mission.getDuration() - flight.getDuration());

                    Log.d("DBHelper", "updateFlightInMission");
                    flight.setDate(date);
                    flight.setDuration(duration);
                    flight.setDay(isDay);
                    mission.setDuration(mission.getDuration() + duration);
                    if (isDay)
                        mission.setDuration(mission.getDayDuration() + duration);
                    else
                        mission.setDuration(mission.getNightDuration() + duration);
                }
            }
        });
    }

    @Override
    public void updateMission(final int id, final String city, final Date date) {
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                // begin & end transcation calls are done for you
                MissionDB mission = realm.where(MissionDB.class)
                        .equalTo("id", id)
                        .findFirst();
                mission.setDate(date);
                mission.setCity(city);
                Log.d("DBHelper", "updateMission");

            }
        });
    }

    @Override
    public RealmResults<MissionDB> getMissionsYear(int year) {
        Log.d("DBHelper", "getMissionsPerYear" + String.valueOf(year));
        GregorianCalendar calendarFrom = new GregorianCalendar(year,
                Calendar.JANUARY, 1);
        GregorianCalendar calendarTo = new GregorianCalendar(year,
                Calendar.DECEMBER, 31);
        RealmResults<MissionDB> result = realm.where(MissionDB.class)
                .between("date", calendarFrom.getTime(), calendarTo.getTime())
                .findAll();
        return result;
    }

    @Override
    public Map<String, YearEntity> getDataOfYear() {
        RealmResults<MissionDB> result = realm.where(MissionDB.class).findAll();
        result = result.sort("date", Sort.DESCENDING);
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        long duration = 0;
        long dayDuration = 0;
        long nightDuration = 0;


        Map<String, YearEntity> data = new HashMap<String, YearEntity>();
        YearEntity yearEntity = null;

        if (!result.isEmpty())
            for (final MissionDB c : result) {

                String year = df.format(c.getDate());
                duration = c.getDuration();
                dayDuration = c.getDayDuration();
                nightDuration = c.getNightDuration();

                if (data.containsKey(year)) {

                    yearEntity.setCountHoursInYear(yearEntity.getCountHoursInYear() + data.get(year).getCountHoursInYear());
                    yearEntity.setCountDayHours(yearEntity.getCountDayHours() +  data.get(year).getCountDayHours());
                    yearEntity.setCountHoursInYear(yearEntity.getCountNightHours() +  data.get(year).getCountNightHours());
                    data.put(year, yearEntity);
                } else {
                    yearEntity = new YearEntity(String.format(context.getString(R.string.format_year), year), duration, dayDuration, nightDuration);
                    data.put(year, yearEntity);
                }
            }
        return data;
    }

    public int getPrimaryKey(RealmObject object) {
        return realm.where(object.getClass()).max("id").intValue() + 1;
    }

    public void closeRealm(RealmChangeListener listener) {
        realm.removeChangeListener(listener);
        realm.close();
    }

    public void closeRealm() {
        realm.close();
    }
}
