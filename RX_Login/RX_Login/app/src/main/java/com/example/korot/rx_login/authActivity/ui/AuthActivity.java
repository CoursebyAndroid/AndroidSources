package com.example.korot.rx_login.authActivity.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.crashlytics.android.Crashlytics;
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
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.api.GoogleApiClient;
import javax.inject.Inject;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;


public class AuthActivity extends BaseActivity implements LoginFragment.ILogin,ForgotPassDialog.IFogot,RegistrationFragment.IRegist
,IBaseView.IAuthView {

    private static final String TAG = AuthActivity.class.getSimpleName();
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private GoogleApiClient mGoogleApiClient;

    @Inject
    AuthPresenterImpl presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        this.addFragment(R.id.activity_main, LoginFragment.newInstance(), "Auth");
        presenter.init(this);
    }

    public CallbackManager getCallbackManager() {
        return callbackManager;
    }

    public LoginManager getLoginManager() {
        return loginManager;
    }

//    public AppCompatButton getmBtnTwitter() {
//        return mBtnTwitter;
//    }

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
        if (requestCode == SocialControllerImpl.FACEBOOK_SELECT) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == SocialControllerImpl.GOOGLE_SELECT && resultCode == RESULT_OK) {
              mGoogleApiClient.connect();
        }
//        if(requestCode == SocialControllerImpl.TWITTER_SELECT) {
//            mBtnTwitter.onActivityResult(requestCode, resultCode, data);
//        }
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
    public void onFacebookClickListener() {
        Log.e(TAG, " onFacebookClickListener()");
        presenter.inSelect(1);
    }

    @Override
    public void onGoogleClickListener() {
        Log.e(TAG, " onGoogleClickListener()");
      presenter.inSelect(9001);
    }

    @Override
    public void onTwitterClickListener() {
        Log.e(TAG, " onTwitterClickListener()");
        presenter.inSelect(2);
    }

    @Override
    public void onInstagramClickListener() {
        Log.e(TAG, " onInstagramClickListener()");
        presenter.inSelect(3);
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
}



