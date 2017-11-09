package com.example.korot.rx_login.app.daggerApp;

import android.app.Application;

import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.app.utils.IValidator;
import com.example.korot.rx_login.app.utils.NetworkCheckImpl;
import com.example.korot.rx_login.app.utils.RealmService;
import com.example.korot.rx_login.app.utils.Validator;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by korot on 24.09.2017.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public Application provideApplication() {
        return application;
    }

    @Provides
    @AppScope
    IValidator provideValidator() {
        return new Validator();
    }

    @Provides
    @AppScope
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @AppScope
    IRealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }

    @Provides
    @AppScope
    INetworkCheck provideNetworkCheck() { return new NetworkCheckImpl(application); }
}