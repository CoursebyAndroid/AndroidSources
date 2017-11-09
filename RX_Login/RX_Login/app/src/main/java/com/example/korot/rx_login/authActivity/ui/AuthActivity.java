package com.example.korot.rx_login.authActivity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.daggerApp.AppComponent;
import com.example.korot.rx_login.app.model.User;
import com.example.korot.rx_login.authActivity.sosial.SocialControllerImpl;
import com.example.korot.rx_login.basePackage.BaseActivity;
import com.example.korot.rx_login.authActivity.daggerAuth.AuthModule;
import com.example.korot.rx_login.authActivity.utils.AuthPresenterImpl;
import com.example.korot.rx_login.basePackage.IBaseView;
import com.example.korot.rx_login.basePackage.IOnBackPressed;
import com.example.korot.rx_login.authActivity.fogotPassFragment.ui.ForgotPassDialog;
import com.example.korot.rx_login.authActivity.loginFragment.ui.LoginFragment;
import com.example.korot.rx_login.authActivity.registFragment.ui.RegistrationFragment;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import javax.inject.Inject;
import butterknife.ButterKnife;


public class AuthActivity extends BaseActivity implements LoginFragment.ILogin,ForgotPassDialog.IFogot,RegistrationFragment.IRegist
,IBaseView.IAuthView, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = AuthActivity.class.getSimpleName();

    @Inject
    AuthPresenterImpl presenter;
    @Inject
    SocialControllerImpl sosial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sosialClientTwitter = new TwitterAuthClient();
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();

        this.addFragment(R.id.activity_main, LoginFragment.newInstance(), "Auth");
        presenter.init(this);
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

    public TwitterAuthClient getSosialClientTwitter() {
        return sosialClientTwitter;
    }

    public GoogleApiClient getGoogleApiClient(){
        return mGoogleApiClient;
    }

    @Override
    protected void setupComponent(AppComponent appComponent) {
        appComponent.plus(new AuthModule(this)).inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, " onActivityResult " + requestCode + " " + resultCode);
            sosialClientTwitter.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 9001) {
                mGoogleApiClient.connect();
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                Log.e(TAG,"GoogleSignInResult result " + String.valueOf((result.getStatus())));
                Log.e(TAG,"GoogleSignInResult result " + String.valueOf((result.isSuccess())));
                googleToken(result);
            }

    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            googleToken(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    googleToken(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onLoginClickListener(String email, String pass) {
        if(email != null && pass != null){
            Log.e(TAG, " onLoginClickListener() email - " + email + " password - " + pass);
            presenter.login(email, pass);
        }
    }

    @Override
    public void onRegistClickListener(String phone, String email, String pass) {
        if(email != null && pass != null){
            Log.e(TAG, " onLoginClickListener() phone - " + phone + " email - " + email + " password - " + pass);
            presenter.singUp(phone, email, pass);
        }
    }

    @Override
    public void onRegisterClickListener() {
        Log.e(TAG, " onRegisterClickListener() ");
        this.replaceFragment(R.id.activity_main, RegistrationFragment.newInstance(), "Auth");
    }

    @Override
    public void onForgotPassClickListener() {
        Log.e(TAG, " onForgotPassClickListener() ");
        this.addDialog(ForgotPassDialog.newInstance(), "Forgot password?");
    }

    @Override
    public void onSosialClickListener(Integer select) {
        Log.e(TAG, " onSosialClickListener() ");
            presenter.inSelect(select);
    }


    @Override
    public void onFogotClickListener(String email) {
        if(email != null){
            Log.e(TAG, " onForgotPassClickListener() email - " + email);
            presenter.forgotPassword(email);
        }
    }


    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        IOnBackPressed iOnBackPressed = null;
        for (Fragment fragment: fm.getFragments()){
            if (fragment instanceof IOnBackPressed){
                iOnBackPressed.onBack();
            }
        }
        super.onBackPressed();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void isAuth(User user) {
        if(user != null){
            Toast.makeText(AuthActivity.this, user.toString(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(AuthActivity.this, "HUJ", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void isFogot(String masage) {
        Log.e(TAG,"isFogot"+ masage);
        if(masage != null){
            Toast.makeText(AuthActivity.this, masage, Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(AuthActivity.this, "HUJ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void googleToken(GoogleSignInResult result) {
        if (result.isSuccess()) {
            Log.d(TAG, "Result:" + result.isSuccess());
            GoogleSignInAccount acct = result.getSignInAccount();
            String authCode = null;
            if (acct != null) {
                authCode = acct.getServerAuthCode().toString();
            }
            Log.e(TAG, "googleToken " + authCode);
            if(authCode != null) {
                sosial.googleTokenRealm(authCode);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}




