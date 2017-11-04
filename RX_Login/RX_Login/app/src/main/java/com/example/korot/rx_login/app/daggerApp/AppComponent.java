package com.example.korot.rx_login.app.daggerApp;

import com.example.korot.rx_login.authActivity.daggerAuth.AuthComponent;
import com.example.korot.rx_login.authActivity.daggerAuth.AuthModule;
import com.example.korot.rx_login.deteilActivity.daggerDeteil.DeteilComponent;
import com.example.korot.rx_login.deteilActivity.daggerDeteil.DeteilModule;
import com.example.korot.rx_login.mainActivity.daggerMain.MainComponent;
import com.example.korot.rx_login.mainActivity.daggerMain.MainModule;

import dagger.Component;

/**
 * Created by korot on 24.09.2017.
 */
@AppScope
@Component (modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    AuthComponent plus (AuthModule authModule);
    MainComponent plus (MainModule mainModule);
    DeteilComponent plus (DeteilModule deteilModule);
}