package com.example.korot.rx_login.authActivity.sosial;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.daggerApp.MyApp;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.example.korot.rx_login.basePackage.BaseSosial;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.AccountPicker;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import java.util.Arrays;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SocialControllerImpl extends BaseSosial implements ISocialController {

    private static final String TAG = SocialControllerImpl.class.getSimpleName();

    @Inject
    public SocialControllerImpl(AuthActivity activity, INetworkCheck networkCheck) {
        this.activity = activity;
        this.networkCheck = networkCheck;
    }


    @Override
    public void onSelect(int select) {
        switch (select) {
            case R.integer.facebook:
                Log.e(TAG ,"Facebook");
                logiInFacebook();
                break;
            case R.integer.google:
                Log.e(TAG ,"Google");
                loginInGoogle();
                break;
            case R.integer.twitter:
                Log.e(TAG ,"Twitter");
                loginButton = activity.getmBtnTwitter();
                loginInTwitter();
                break;
            case R.integer.instagram:
                Log.e(TAG ,"Instagram");
                loginInInstagram();
                break;
            default:
                throw new NullPointerException("Null onSelect()");
        }

    }


    public void logiInFacebook() {
        if (!networkCheck.isOnline()) {
            Toast.makeText(activity, activity.getString(R.string.error_text_no_internet), Toast.LENGTH_LONG).show();
            return;
        }
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
        activity.getLoginManager().registerCallback(activity.getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final String token = AccessToken.getCurrentAccessToken().getToken();
                Toast.makeText(activity,"Enter Facebook",Toast.LENGTH_LONG).show();
                Log.e(TAG + " Facebook",token.toString());
            }

            @Override
            public void onCancel() {}

            @Override
            public void onError(FacebookException exception) {}
        });

    }

    public void loginInGoogle(){
        if (!networkCheck.isOnline()) {
            Toast.makeText(activity, activity.getString(R.string.error_text_no_internet), Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        Toast.makeText(activity,"Enter Google",Toast.LENGTH_LONG).show();
        activity.startActivityForResult(intent,9001);
    }


    public void loginInTwitter() {
        activity.getmBtnTwitter().setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                final String token = AccessToken.getCurrentAccessToken().getToken();
                Toast.makeText(activity,"Enter Twiter",Toast.LENGTH_LONG).show();
                Log.e(TAG + " Twiter",token.toString());
            }
            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(activity, "Authentication failed!", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void loginInInstagram() {
        checkForInstagramData();
        final Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https")
                .authority("api.instagram.com")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id", "6a88875d4ca54d1c9f154d869d979f1d")
                .appendQueryParameter("redirect_uri", "http://www.w3.org/Addressing/URL/url-spec.txt")
                .appendQueryParameter("response_type", "token");
        final Intent browser = new Intent(Intent.ACTION_VIEW, uriBuilder.build());
        activity.startActivity(browser);
    }

    private void checkForInstagramData() {
        final Uri data = activity.getIntent().getData();
        if(data != null && data.getScheme().equals("http://www.w3.org/Addressing/URL/url-spec.txt") && data.getFragment() != null) {
            final String accessToken = data.getFragment().replaceFirst("access_token=", "");
            if (accessToken != null) {
                handleSignInResult(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        // Do nothing, just throw the access token away.

                        return null;
                    }
                });
            } else {
                handleSignInResult(null);
            }
        }
    }

    private void handleSignInResult(Callable<Void> logout) {
        if(logout == null) {
            /* Login error */
            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
        }
    }


}