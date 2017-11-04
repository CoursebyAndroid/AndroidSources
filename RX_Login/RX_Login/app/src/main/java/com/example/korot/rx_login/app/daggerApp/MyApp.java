package com.example.korot.rx_login.app.daggerApp;

import android.app.Application;
import android.content.Context;


import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by korot on 24.09.2017.
 */

public class MyApp extends Application {

    private static AppComponent appComponent;

//    protected static MyApp instance;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
//        instance = this;
        initRealmConfiguration();
        initAppComponent();

    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).apiModule(new ApiModule()).build();


    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
