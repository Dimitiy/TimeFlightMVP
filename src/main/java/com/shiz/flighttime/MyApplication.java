package com.shiz.flighttime;

import android.app.Application;
import android.content.Context;

import com.shiz.flighttime.utils.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

//import butterknife.ButterKnife;

/**
 * Created by OldMan on 14.02.2016.
 */

public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context = getContext();
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    public static RealmConfiguration mRealmConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        Realm.setDefaultConfiguration(getRealmConfigeration());
    }
    public RealmConfiguration getRealmConfigeration() {
        if (mRealmConfig == null) {
            mRealmConfig = new RealmConfiguration.Builder(this)
                    .schemaVersion(Constants.VersionRealm)
                    .deleteRealmIfMigrationNeeded()
                    .build();
        }
        return mRealmConfig; // Automatically run migration if needed
    }
}