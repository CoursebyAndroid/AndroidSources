package com.example.korot.rx_login.basePackage;

import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

/**
 * Created by Root on 06.11.2017.
 */

public abstract class BaseSosial {
    protected AuthActivity activity;
    protected INetworkCheck networkCheck;
    protected TwitterLoginButton loginButton;
}
