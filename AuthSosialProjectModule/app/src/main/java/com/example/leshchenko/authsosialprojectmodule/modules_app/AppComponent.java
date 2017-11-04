package com.example.leshchenko.authsosialprojectmodule.modules_app;

import dagger.Component;

/**
 * Created by Root on 31.10.2017.
 */
@AppScope
@Component(modules = {AppModule.class,ApiModule.class})
public interface AppComponent {
    SubComponent plus(SplashModule module);
}
