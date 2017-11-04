package com.example.leshchenko.authsosialprojectmodule.modules_app;

import android.app.Application;
import android.support.annotation.NonNull;


import com.example.leshchenko.authsosialprojectmodule.dao.IRealmService;
import com.example.leshchenko.authsosialprojectmodule.dao.RealmService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Root on 31.10.2017.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    @AppScope
    public Application provideApplication() {
        return application;
    }

    @Provides
    @NonNull
    @AppScope
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @NonNull
    @AppScope
    IRealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}
