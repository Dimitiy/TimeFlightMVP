package com.shiz.flighttime.database;


import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.Date;

import io.realm.MissionDBRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * Created by OldMan on 13.02.2016.
 */
@RealmClass
@Parcel(implementations = {MissionDBRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {MissionDB.class})
public class MissionDB extends RealmObject {
    @Required // Name is not nullable
    private String city;
    @PrimaryKey
    private int id;
    @Required // Name is not nullable
    private Date date;
    private long duration;
    private long dayDuration;

    public long getNightDuration() {
        return nightDuration;
    }

    public void setNightDuration(long nightDuration) {
        this.nightDuration = nightDuration;
    }

    public long getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(long dayDuration) {
        this.dayDuration = dayDuration;
    }

    private long nightDuration;

    private RealmList<FlightDB> flightDBRealmList;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<FlightDB> getFlightDBRealmList() {
        return flightDBRealmList;
    }

    @ParcelPropertyConverter(FlightDBListParcelConverter.class)
    public void setFlightDBRealmList(RealmList<FlightDB> flightDBRealmList) {
        this.flightDBRealmList = flightDBRealmList;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Your constructor and any other accessor
     *  methods should go here.
     */

}
