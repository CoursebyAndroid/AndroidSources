package com.example.korot.rx_login.authActivity.daggerAuth;

import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.authActivity.sosial.ISocialController;
import com.example.korot.rx_login.authActivity.sosial.SocialControllerImpl;
import com.example.korot.rx_login.authActivity.utils.AuthInteractorImpl;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.example.korot.rx_login.basePackage.IInteractorContract;
import dagger.Module;
import dagger.Provides;

@Module
public class AuthModule {

    private AuthActivity authActivity;

    public AuthModule(AuthActivity authActivity) {
        this.authActivity = authActivity;
    }

    @Provides
    @AuthScope
    AuthActivity provideAuthActivity() {
        return authActivity;
    }

    @Provides
    @AuthScope
    IInteractorContract.IAuthInteractor provideAuthInteractor(IApiServise api) {
        return new AuthInteractorImpl(api);
    }

    @Provides
    @AuthScope
    ISocialController provideSocialController(AuthActivity activity, INetworkCheck networkCheck, IRealmService realmService) {
        return new SocialControllerImpl(activity, networkCheck, realmService);
    }
}

