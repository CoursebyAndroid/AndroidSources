package com.example.korot.rx_login.authActivity.sosial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Root on 04.11.2017.
 */

public class GoogleAuthHelper implements IGoogleAuthHelper{
    private static final String TAG = GoogleAuthHelper.class.getSimpleName();

    private  AuthActivity authActivity;
    private INetworkCheck networkCheck;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleSignInResult googleSignInResult;

    public GoogleAuthHelper(AuthActivity activity,INetworkCheck networkCheck) {
        this.authActivity = activity;
        this.networkCheck = networkCheck;
    }

    public void configure(Context context) {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();
        initClient();
    }

    private void initClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(authActivity)
                .enableAutoManage(authActivity, (GoogleApiClient.OnConnectionFailedListener) authActivity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
    }

}
