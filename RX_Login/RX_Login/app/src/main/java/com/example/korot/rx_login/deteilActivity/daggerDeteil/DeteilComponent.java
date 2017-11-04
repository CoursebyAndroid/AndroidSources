package com.example.korot.rx_login.deteilActivity.daggerDeteil;

import com.example.korot.rx_login.authActivity.daggerAuth.AuthScope;
import com.example.korot.rx_login.deteilActivity.ui.DeteilActivity;

import dagger.Subcomponent;

/**
 * Created by korot on 28.09.2017.
 */

@DeteilScope
@Subcomponent(
        modules = DeteilModule.class
)
public interface DeteilComponent {
    void inject (DeteilActivity deteilActivity);
}
