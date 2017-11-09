package com.example.korot.rx_login.authActivity.daggerAuth;

import com.example.korot.rx_login.authActivity.ui.AuthActivity;
//import com.example.korot.rx_login.authActivity.fogotPassFragment.daggerFogotPass.FogotPassComponent;
//import com.example.korot.rx_login.authActivity.fogotPassFragment.daggerFogotPass.FogotPassModule;
//import com.example.korot.rx_login.authActivity.loginFragment.dagerLogin.LoginComponent;
//import com.example.korot.rx_login.authActivity.loginFragment.dagerLogin.LoginModule;
//import com.example.korot.rx_login.authActivity.registFragment.daggerRegist.RegistComponent;
//import com.example.korot.rx_login.authActivity.registFragment.daggerRegist.RegistModule;

import dagger.Subcomponent;

@AuthScope
@Subcomponent(
        modules = AuthModule.class
)
public interface AuthComponent {
    void inject (AuthActivity authActivity);
}

