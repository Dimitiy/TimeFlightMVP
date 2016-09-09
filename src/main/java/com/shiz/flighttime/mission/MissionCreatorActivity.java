package com.shiz.flighttime.mission;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.shiz.flighttime.R;
import com.shiz.flighttime.database.MissionDB;
import com.shiz.flighttime.fragment.BaseNameFragment;
import com.shiz.flighttime.fragment.CityNameFragment;
import com.shiz.flighttime.fragment.DateFragment;
import com.shiz.flighttime.fragment.DurationFragment;
import com.shiz.flighttime.fragment.PlaceFragment;
import com.shiz.flighttime.fragment.TypeFlightFragment;
import com.shiz.flighttime.listener.CityChangeListener;
import com.shiz.flighttime.listener.DatePickerListener;
import com.shiz.flighttime.listener.TimePickerListener;
import com.shiz.flighttime.listener.TypeFlightPickerListener;
import com.shiz.flighttime.main.MainActivity;
import com.shiz.flighttime.utils.Connecter;
import com.shiz.flighttime.utils.Constants;
import com.shiz.flighttime.utils.GoogleClient;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import me.drozdzynski.library.steppers.OnCancelAction;
import me.drozdzynski.library.steppers.OnFinishAction;
import me.drozdzynski.library.steppers.SteppersItem;
import me.drozdzynski.library.steppers.SteppersView;

public class MissionCreatorActivity extends AppCompatActivity implements MissionCreatorView {
    private final int ZERO_STEPS = 0;
    private final int FIRST_STEPS = 1;
    private final int SECOND_STEPS = 2;

    private String nameCity = null;
    private Calendar calendarDate;
    private Date previousDate = null;
    private long previousDuration = 0;
    private boolean isDayFligth = true;
    private long duration = 0;
    private ProgressBar progressBar;
    private MissionCreatorPresenterImpl presenter;
    private boolean isUpdateMission = false;
    private MissionDB mission;
    int typeOfActivity, missionID, flightID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_mission);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.create_mission_status));
        }
        if (savedInstanceState != null) {
            typeOfActivity = savedInstanceState.getInt("save_type_of_activity");
            missionID = savedInstanceState.getInt("save_mission_id");
            flightID = savedInstanceState.getInt("save_flight_id");
            mission = Parcels.unwrap(savedInstanceState.getParcelable("save_mission"));
        } else {
            Intent intent = getIntent();
            typeOfActivity = intent.getIntExtra("type_of_activity", -1);
            missionID = intent.getIntExtra("mission_id", -1);
            flightID = intent.getIntExtra("flight_id", -1);
            mission = Parcels.unwrap(getIntent().getParcelableExtra("mission"));
        }
        Log.d("Mission", typeOfActivity + " " + missionID + flightID + nameCity);

        if (typeOfActivity == Constants.TYPE_OF_MISSION_ACTIVITY_CHANGED && mission != null) {
            nameCity = mission.getCity();
            previousDate = mission.getDate();
        } else if (typeOfActivity == Constants.TYPE_OF_FLIGHT_ACTIVITY_CHANGED && mission != null) {
            previousDate = mission.getFlightDBRealmList().get(flightID).getDate();
            previousDuration = mission.getFlightDBRealmList().get(flightID).getDuration();
            isDayFligth = mission.getFlightDBRealmList().get(flightID).isDay();
        }
        Log.d("Mission", previousDate + " " + previousDuration + " " + nameCity);

        calendarDate = Calendar.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progress);
        presenter = new MissionCreatorPresenterImpl(this);
        initSteppers(typeOfActivity, missionID, flightID, mission);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Сохраните состояние UI в переменную savedInstanceState.
        // Она будет передана в метод onCreate при закрытии и
        // повторном запуске процесса.
        savedInstanceState.putParcelable("save_mission", Parcels.wrap(mission));
        savedInstanceState.putInt("save_type_of_activity", typeOfActivity);
        savedInstanceState.putInt("save_mission_id", missionID);
        savedInstanceState.putInt("save_flight_id", flightID);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Восстановите состояние UI из переменной savedInstanceState.
        // Этот объект типа Bundle также был передан в метод onCreate.
    }

    private void initSteppers(final int typeOfActivity, final int missionID, final int flightID, final MissionDB mission) {
        SteppersView.Config steppersViewConfig = new SteppersView.Config();
        steppersViewConfig.setOnFinishAction(new OnFinishAction() {
            @Override
            public void onFinish() {
                switch (typeOfActivity) {
                    case Constants.TYPE_OF_MISSION_ACTIVITY_CREATED:
                        presenter.createMission(nameCity, calendarDate);
                        break;
                    case Constants.TYPE_OF_MISSION_ACTIVITY_CHANGED:
                        if (missionID != -1)
                            presenter.updateMission(missionID, nameCity, calendarDate);
                        break;
                    case Constants.TYPE_OF_FLIGHT_ACTIVITY_CREATED:
                        if (missionID != -1)
                            presenter.createFlight(missionID, calendarDate, duration, isDayFligth);
                        break;
                    case Constants.TYPE_OF_FLIGHT_ACTIVITY_CHANGED:
                        if (missionID != -1 && flightID != -1)
                            presenter.updateFlight(missionID, mission.getFlightDBRealmList().get(flightID).getId(), calendarDate, duration, isDayFligth);
                        break;
                    default:
                        presenter.createMission(nameCity, calendarDate);
                        break;
                }
            }
        });

        steppersViewConfig.setOnCancelAction(new OnCancelAction() {
            @Override
            public void onCancel() {
                MissionCreatorActivity.this.startActivity(new Intent(MissionCreatorActivity.this, MainActivity.class));
                MissionCreatorActivity.this.finish();
            }
        });
        steppersViewConfig.setFragmentManager(getSupportFragmentManager());
        ArrayList<SteppersItem> steps = new ArrayList<>();
        if (typeOfActivity == Constants.TYPE_OF_MISSION_ACTIVITY_CREATED || typeOfActivity == Constants.TYPE_OF_MISSION_ACTIVITY_CHANGED) {
            steps.add(ZERO_STEPS, getCityFragment());
            steps.add(FIRST_STEPS, getDateFragment());
        } else {
            steps.add(ZERO_STEPS, getDateFragment());
            steps.add(FIRST_STEPS, getDurationFragment());
            steps.add(SECOND_STEPS, getTypeFlightFragment());
        }
        SteppersView steppersView = (SteppersView) findViewById(R.id.steppersView);
        steppersView.setConfig(steppersViewConfig);
        steppersView.setItems(steps);
        steppersView.build();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private SteppersItem getCityFragment() {
        final SteppersItem item = new SteppersItem();
        BaseNameFragment cityFragment;
//        if (GoogleClient.checkGooglePlayServicesAvailable(getApplicationContext()) && Connecter.isNetworkAvailable(getApplicationContext()))
//            cityFragment = PlaceFragment.newInstance(nameCity);
//        else
        cityFragment = CityNameFragment.newInstance(nameCity);

        item.setLabel(getResources().getString(R.string.enter_travel_village));
        item.setSubLabel(getResources().getString(R.string.home_airfield));
        item.setPositiveButtonEnable(false);
        cityFragment.addCityNameChangedListener(new CityChangeListener() {
            @Override
            public void onNameCityChange(String city) {
                if (city.length() >= 3) {
                    item.setPositiveButtonEnable(true);
                    nameCity = city;
                } else
                    item.setPositiveButtonEnable(false);
            }
        });
        item.setFragment(cityFragment);
        return item;
    }

    private SteppersItem getDateFragment() {
        final SteppersItem item = new SteppersItem();
        if (previousDate != null)
            calendarDate.setTime(previousDate);
        if (typeOfActivity == Constants.TYPE_OF_MISSION_ACTIVITY_CREATED || typeOfActivity == Constants.TYPE_OF_MISSION_ACTIVITY_CHANGED)
            item.setLabel(getResources().getString(R.string.choose_date));
        else item.setLabel(getResources().getString(R.string.choose_flight_date));

        DateFragment dateFragment = DateFragment.newInstance(previousDate);
        dateFragment.addDatePickerListener(new DatePickerListener() {
            @Override
            public void onSelectDate(Calendar calendarMission) {
                calendarDate.set(calendarMission.get(Calendar.YEAR),
                        calendarMission.get(Calendar.MONTH),
                        calendarMission.get(Calendar.DAY_OF_MONTH));
            }
        });

        item.setFragment(dateFragment);
        return item;
    }

    private SteppersItem getDurationFragment() {
        final SteppersItem item = new SteppersItem();
        DurationFragment durationFragment = DurationFragment.newInstance(previousDuration);
        durationFragment.addTimePickerListener(new TimePickerListener() {
            @Override
            public void onSelectTimeCount(long calendarTime) {
                MissionCreatorActivity.this.duration = calendarTime;
            }
        });
        item.setLabel(getResources().getString(R.string.choose_count_time));
        item.setFragment(durationFragment);
        return item;
    }

    private SteppersItem getTypeFlightFragment() {
        final SteppersItem item = new SteppersItem();
        TypeFlightFragment typeFlightFragment = TypeFlightFragment.newInstance(isDayFligth);
        typeFlightFragment.addTypeFlightPickerListener(new TypeFlightPickerListener() {
            @Override
            public void onTypeFlightSelected(boolean isDay) {
                MissionCreatorActivity.this.isDayFligth = isDay;
            }
        });
        item.setLabel(getResources().getString(R.string.pick_type_flight));
        item.setFragment(typeFlightFragment);
        return item;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateToMainView() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

}
