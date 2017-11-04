package com.example.leshchenko.authsosialprojectmodule.modules_app;





import com.example.leshchenko.authsosialprojectmodule.ui.AuthActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Root on 31.10.2017.
 */
@Module
public class SplashModule {
    private AuthActivity splashActivity;
    public SplashModule(AuthActivity splashActivity) {
        this.splashActivity = splashActivity;
    }

    @Provides
    @SubScope
    AuthActivity provideSplashActivity() {
        return splashActivity;
    }
}
