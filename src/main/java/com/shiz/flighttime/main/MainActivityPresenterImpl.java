/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.shiz.flighttime.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.shiz.flighttime.R;
import com.shiz.flighttime.data.YearEntity;
import com.shiz.flighttime.database.DBHelper;
import com.shiz.flighttime.database.MissionDB;
import com.shiz.flighttime.mission.MissionCreatorActivity;
import com.shiz.flighttime.utils.Constants;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmChangeListener;
import za.co.riggaroo.materialhelptutorial.TutorialItem;


public class MainActivityPresenterImpl implements MainActivityPresenter, MainInteractor.OnMissionFinishedListener, MainInteractor.OnYearsFinishedListener {

    private MainActivityView mainView;
    private MainInteractor findItemsInteractor;
    private DBHelper dbHelper;
    private Context context;
    private String TAG = MainActivityPresenterImpl.class.getSimpleName();

    public MainActivityPresenterImpl(MainActivityView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
        dbHelper = new DBHelper();
        findItemsInteractor = new MainInteractorImpl(context);
    }

    @Override
    public void onResume() {
//        findItemsInteractor.findYearsItems(dbHelper, this);
    }



    @Override
    public void onFindYearsItems() {
        findItemsInteractor.findYearsItems(dbHelper, this);

    }

    @Override
    public void onMissionItems(String year) {
        if (mainView != null) {
            mainView.showProgress();
        }
        findItemsInteractor.findMissionItems(dbHelper, year, this);
    }


    @Override
    public void navigateToCreateMission() {
        Intent intent = new Intent(context, MissionCreatorActivity.class);
        intent.putExtra("type_of_activity", Constants.TYPE_OF_MISSION_ACTIVITY_CREATED);
        mainView.onMissionCreate(intent);
    }

    @Override
    public void navigateToCreateFlight(MissionDB mission) {
        Intent intent = new Intent(context, MissionCreatorActivity.class);
        intent.putExtra("type_of_activity", Constants.TYPE_OF_FLIGHT_ACTIVITY_CREATED);
        intent.putExtra("mission_id", mission.getId());
        mainView.onCreateFlight(intent);
    }

    @Override
    public void navigateToChangeMission(MissionDB mission, int missionID) {
        Intent intent = new Intent(context, MissionCreatorActivity.class);
        intent.putExtra("type_of_activity", Constants.TYPE_OF_MISSION_ACTIVITY_CHANGED);
        intent.putExtra("mission", Parcels.wrap(mission));
        intent.putExtra("mission_id", mission.getId());
        mainView.onChangeMission(intent);
    }

    @Override
    public void navigateToChangeFlight(MissionDB mission, int groupPosition, int childPosition) {
        Intent intent = new Intent(context, MissionCreatorActivity.class);
        intent.putExtra("type_of_activity", Constants.TYPE_OF_FLIGHT_ACTIVITY_CHANGED);
        intent.putExtra("mission", Parcels.wrap(mission));
        intent.putExtra("mission_id", mission.getId());
        intent.putExtra("flight_id", childPosition);
        mainView.onChangeFlight(intent);
    }

    @Override
    public void onDeleteMission(final int groupPosition) {
        if (mainView != null)
            mainView.showProgress();

        if (dbHelper != null) {
            dbHelper.addListener(new RealmChangeListener() {
                @Override
                public void onChange(Object element) {
                    Log.d(TAG, "deleted mission" + groupPosition);
                    dbHelper.deleteListener(this);
                    if (mainView != null) {
                        mainView.notifyOnGroupItemRemoved();
                        mainView.hideProgress();
                    }
                }
            });
            dbHelper.deleteMission(groupPosition);
        }
    }

    @Override
    public void onDeleteFlight(final int groupPosition, final int childPosition) {
        if (mainView != null)
            mainView.showProgress();

        if (dbHelper != null) {
            dbHelper.addListener(new RealmChangeListener() {
                @Override
                public void onChange(Object element) {
                    Log.d(TAG, "delete flight in " + groupPosition + " " + childPosition);
                    dbHelper.deleteListener(this);
                    if (mainView != null) {
                        mainView.hideProgress();
                    }
                }
            });
            dbHelper.deleteFlightInMission(groupPosition, childPosition);

        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
        dbHelper.closeRealm();

    }

    @Override
    public void onGetTutorialItems() {
        TutorialItem tutorialItem1 = new TutorialItem(context.getString(R.string.work_with_mission), context.getString(R.string.tutorial_work_with_mission),
                R.color.menu_help, R.drawable.left_swipe_tutorial);
        TutorialItem tutorialItem2 = new TutorialItem(context.getString(R.string.deleting_data), context.getString(R.string.tutorial_deleting_data),
                R.color.menu_invite_friends, R.drawable.right_swipe_tutorial);
        TutorialItem tutorialItem3 = new TutorialItem(context.getString(R.string.backup), context.getString(R.string.text_for_backup),
                R.color.text_button_menu_preference, R.drawable.ic_transparent_button, R.drawable.ic_export_web);
//
        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        if (mainView != null) {
            mainView.loadTutorial(tutorialItems);
        }
    }

    @Override
    public void onYearsFinished(ArrayList<YearEntity> yearsList) {
        if (mainView != null) {
            mainView.setYears(yearsList);
        }
    }

    @Override
    public void onMissionFinished(List<MissionDB> missionsList) {
        if (mainView != null) {
            Log.d(TAG, "on missionFinished");
            mainView.setMissionItems(missionsList);
            mainView.hideProgress();
        }
    }
}
