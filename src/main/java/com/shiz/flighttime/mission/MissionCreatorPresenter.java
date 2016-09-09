package com.shiz.flighttime.mission;

import java.util.Calendar;

/**
 * Created by oldman on 19.05.16.
 */
interface MissionCreatorPresenter {
    void onResume();

    void onPause();

    void onDestroy();

    void createMission(String name, Calendar date);

    void createFlight(int missionId, Calendar date, long duration, boolean isDay);

    void updateMission(int id, String nameCity, Calendar calendarDate);

    void updateFlight(int idMission, int idFlight, Calendar date, long duration, boolean isDay);
}
