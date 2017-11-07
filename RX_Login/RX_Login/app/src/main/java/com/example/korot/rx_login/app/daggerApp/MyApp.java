package com.example.korot.rx_login.app.daggerApp;

import android.app.Application;
import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.example.korot.rx_login.R;
import com.facebook.FacebookSdk;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by korot on 24.09.2017.
 */

public class MyApp extends Application {

    private static AppComponent appComponent;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        TwitterAuthConfig authConfig =
                new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                        getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET));
        Fabric.with(this, new Twitter(authConfig));
        FacebookSdk.sdkInitialize(getApplicationContext());
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
