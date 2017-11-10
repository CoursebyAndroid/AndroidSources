package com.example.korot.rx_login.authActivity.daggerAuth;

import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import dagger.Subcomponent;

@AuthScope
@Subcomponent(
        modules = AuthModule.class
)
public interface AuthComponent {
    void inject (AuthActivity authActivity);
}

