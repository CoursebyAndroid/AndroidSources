package com.example.korot.rx_login.basePackage;

import android.util.Log;

import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.model.UserRealm;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

/**
 * Created by Root on 06.11.2017.
 */

public abstract class BaseSosial {
    protected AuthActivity activity;
    protected INetworkCheck networkCheck;
    protected IRealmService mRealm;


    public void addTokenRealmDao(String token,String selectTag){
        String tag = null;
        switch (selectTag){
            case "Google"  : tag = "Google ";   break;
            case "Facebook": tag = "Facebook "; break;
            case "Twitter" : tag = "Twitter ";  break;
        }
        addRealm(token,tag);
    }

    private void addRealm(String token,String tag){
        mRealm.addUser(new UserRealm(token), UserRealm.class)
                .doOnError(Throwable::getStackTrace)
                .subscribe(
                        next -> {
                            Log.d("UserRealm ", tag + next);
                        },
                        Throwable::getStackTrace
                );
    }
}
