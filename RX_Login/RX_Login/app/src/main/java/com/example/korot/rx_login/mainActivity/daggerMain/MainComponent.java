package com.example.korot.rx_login.mainActivity.daggerMain;

import com.example.korot.rx_login.mainActivity.ui.MainActivity;

import dagger.Subcomponent;

/**
 * Created by korot on 28.09.2017.
 */

@MainScope
@Subcomponent(
        modules = MainModule.class
)
public interface MainComponent {

    void inject (MainActivity mainActivity);
}

