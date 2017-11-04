package com.example.korot.rx_login.authActivity.sosial;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.utils.INetworkCheck;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.common.AccountPicker;
import java.util.Arrays;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import rx.Observable;
import static com.facebook.FacebookSdk.getApplicationContext;



public class SocialControllerImpl implements ISocialController {
    private AuthActivity activity;
    private INetworkCheck networkCheck;
    private boolean successfully = false;
    private String googleIdToken = null;

    public static final int FACEBOOK_SELECT = 1;
    public static final int GOOGLE_SELECT = 9001;
    public static final int TWITTER_SELECT = 2;
    public static final int INSTAGRAM_SELECT = 3;

    private String twitterKey;
    private String twitterSecretKey;


    @Inject
    public SocialControllerImpl(AuthActivity activity, INetworkCheck networkCheck) {
        this.activity = activity;
        this.networkCheck = networkCheck;
    }

    public void initTwitterKey(String key,String secret){
        twitterKey = key;
        twitterSecretKey = secret;
    }

    @Override
    public void onSelect(int select) {
        switch (select) {
            case FACEBOOK_SELECT:
                logiInFacebook();
                break;
            case GOOGLE_SELECT:
                loginInGoogle();
                break;
            case TWITTER_SELECT:
                //loginInTwitter();
                break;
            case INSTAGRAM_SELECT:
              //  loginInInstagram();
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
                Log.e("Token Facebook",token.toString());
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
        if (successfully) {
            Observable.just(googleIdToken).subscribe(user -> {
                Log.d("SosialControler", "User google: " + user.toString());
            }, error -> {
                Log.d("SosialControler", "Error google: " + error.getLocalizedMessage());
            });
        }

        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        Toast.makeText(activity,"Enter Google",Toast.LENGTH_LONG).show();
        activity.startActivityForResult(intent, SocialControllerImpl.GOOGLE_SELECT);
    }

    public void loginInTwitter() {
    }

    private void handleSignInResult(Callable<Void> logout) {
        if(logout == null) {
            /* Login error */
            Toast.makeText(getApplicationContext(), "login_error", Toast.LENGTH_SHORT).show();
        }
    }



    public void loginInInstagram(){
//        checkForInstagramData();
//        final Uri.Builder uriBuilder = new Uri.Builder();
//        uriBuilder.scheme("https")
//                .authority("api.instagram.com")
//                .appendPath("oauth")
//                .appendPath("authorize")
//                .appendQueryParameter("client_id", "6a88875d4ca54d1c9f154d869d979f1d")
//////                  .appendQueryParameter("redirect_uri", "sociallogin://redirect")
//                .appendQueryParameter("redirect_uri", "http://www.w3.org/Addressing/URL/url-spec.txt")
//                .appendQueryParameter("response_type", "token");
//        final Intent browser = new Intent(Intent.ACTION_VIEW, uriBuilder.build());
//        activity.startActivity(browser);
    }

//    private void checkForInstagramData() {
//        final Uri data = activity.getIntent().getData();
//        if(data != null && data.getScheme().equals("sociallogin") && data.getFragment() != null) {
//            final String accessToken = data.getFragment().replaceFirst("access_token=", "");
//            if (accessToken != null) {
//                handleSignInResult(new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//                        // Do nothing, just throw the access token away.
//                        return null;
//                    }
//                });
//            } else {
//                handleSignInResult(null);
//            }
//        }
//    }

//    private void handleSignInResult(Callable<Void> logout) {
//        if(logout == null) {
//            /* Login error */
//            Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
//        }
//    }
}