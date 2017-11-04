package com.example.korot.rx_login.mainActivity.daggerMain;

import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.mainActivity.ui.MainActivity;
import com.example.korot.rx_login.mainActivity.utils.IMainInteractor;
import com.example.korot.rx_login.mainActivity.utils.MainInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korot on 28.09.2017.
 */
@Module
public class MainModule {

    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainScope
    IMainInteractor provideMainInteractor(IApiServise apiService) {
        return new MainInteractorImpl(apiService);
    }
}
