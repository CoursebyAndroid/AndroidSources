package com.example.korot.rx_login.app.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by korot on 24.09.2017.
 */

public class NetworkCheckImpl implements INetworkCheck {
    private Application application;

    public NetworkCheckImpl(Application application) {
        this.application = application;
    }
            @Override
            public boolean isOnline() {
                ConnectivityManager cm =
                        (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                return netInfo != null && netInfo.isConnectedOrConnecting();
            }

}
