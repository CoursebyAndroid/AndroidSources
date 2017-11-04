package com.example.leshchenko.authsosialprojectmodule.modules_app;





import com.example.leshchenko.authsosialprojectmodule.ui.AuthActivity;

import dagger.Subcomponent;

/**
 * Created by Root on 31.10.2017.
 */
@SubScope
@Subcomponent(modules = {SplashModule.class})
public interface SubComponent {
    void inject(AuthActivity AuthActivity);
}
