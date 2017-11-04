package com.example.korot.rx_login.deteilActivity.daggerDeteil;

import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.authActivity.daggerAuth.AuthScope;
import com.example.korot.rx_login.deteilActivity.ui.DeteilActivity;
//import com.example.korot.rx_login.deteilActivity.utils.DeteilInteractorImpl;
import com.example.korot.rx_login.deteilActivity.utils.IDeteilInteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by korot on 28.09.2017.
 */
@Module
public class DeteilModule {
    private DeteilActivity deteilActivity;

    public DeteilModule(DeteilActivity deteilActivity) {
        this.deteilActivity = deteilActivity;
    }

    @Provides
    @DeteilScope
    DeteilActivity provideDeteilActivity() {
        return deteilActivity;
    }

//    @Provides
//    @DeteilScope
//    IDeteilInteractor provideDeteilInteractor(IApiServise apiService) {
//        return new DeteilInteractorImpl(apiService);
//    }
}
