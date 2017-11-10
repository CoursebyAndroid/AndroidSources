package com.example.korot.rx_login.authActivity.sosial;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.model.TestRealm;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.example.korot.rx_login.basePackage.BaseSosial;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import javax.inject.Inject;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SocialControllerImpl extends BaseSosial implements ISocialController {

    private static final String TAG = SocialControllerImpl.class.getSimpleName();
    private ArrayList<TestRealm>list;
    @Inject
    public SocialControllerImpl(AuthActivity activity, INetworkCheck networkCheck, IRealmService realmService) {
        this.activity = activity;
        this.networkCheck = networkCheck;
        this.mRealm = realmService;
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
        LoginManager.getInstance().logInWithReadPermissions(activity, Collections.singletonList("public_profile"));
        activity.getLoginManager().registerCallback(activity.getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(activity,"Enter Facebook",Toast.LENGTH_LONG).show();
                AccessToken accessToken = loginResult.getAccessToken();
                final String token = accessToken.getToken();
                addTokenRealmDao(token,"Facebook");

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
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(activity.getGoogleApiClient());
        activity.startActivityForResult(intent,9001);
    }

    @Override
    public void onResultGoogle(GoogleSignInResult res) {
        if (res.isSuccess()) {
            Log.d(TAG, "Result:" + res.isSuccess());
            GoogleSignInAccount acct = res.getSignInAccount();
            String token = null;
            if (acct != null) {
                token = acct.getServerAuthCode();
            }
            Log.e(TAG, "googleToken " + token);
            if(token != null) {
                Toast.makeText(activity,"Enter Google",Toast.LENGTH_LONG).show();
                Log.e(TAG,"googleTokenRealm " + token);
                addTokenRealmDao(token,"Google");

            }
        }
    }
    @Override
    public void onTestArryaRealm(ArrayList<TestRealm> arr) {
        Log.e("onTestArryaRealm","arr" + arr.size());
        addTest(arr);
    }

    public void loginInTwitter() {
        if (!networkCheck.isOnline()) {
            Toast.makeText(activity, activity.getString(R.string.error_text_no_internet), Toast.LENGTH_LONG).show();
            return;
        }
        activity.getSosialClientTwitter().authorize(activity, new Callback<TwitterSession>() {
            @Override
            public void success(final Result<TwitterSession> result) {
                final TwitterSession sessionData = result.data;
                Log.e("log","twitter");
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String twitter_id = result.data.getUserId() + "";
                String accessToken = result.data.getAuthToken().token;
                String secretToken = result.data.getAuthToken().secret;
                Toast.makeText(activity,"Enter Twitter",Toast.LENGTH_LONG).show();
                Log.i(TAG, "Access Token:- " + accessToken);
                Log.i(TAG, "Secreat Token:- " + secretToken);

                addTokenRealmDao(authToken.toString(),"Twitter");
            }
            @Override
            public void failure(final TwitterException e) {}
        });
    }

    public void loginInInstagram() {
        if (!networkCheck.isOnline()) {
            Toast.makeText(activity, activity.getString(R.string.error_text_no_internet), Toast.LENGTH_LONG).show();
            return;
        }
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
        Toast.makeText(activity,"Enter Instagram",Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
        }
    }
}