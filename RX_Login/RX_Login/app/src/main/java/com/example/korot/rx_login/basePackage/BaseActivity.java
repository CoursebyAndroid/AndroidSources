package com.example.korot.rx_login.basePackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.daggerApp.AppComponent;
import com.example.korot.rx_login.app.daggerApp.MyApp;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    private Unbinder unbinder;
    protected CallbackManager callbackManager;
    protected LoginManager loginManager;
    protected GoogleApiClient mGoogleApiClient;
    protected TwitterAuthClient sosialClientTwitter;
    protected  GoogleSignInOptions googleSignInOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sosialClientTwitter = new TwitterAuthClient();
        callbackManager = CallbackManager.Factory.create();
        loginManager = LoginManager.getInstance();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.DRIVE_APPFOLDER))
                .requestServerAuthCode(getString(R.string.server_client_id), false)
                .build();
        setupComponent(MyApp.get(this).getAppComponent());
        fragmentManager = getSupportFragmentManager();
    }

    protected abstract void setupComponent(AppComponent appComponent);

    protected abstract void initView();

    protected void addDialog(DialogFragment dialog, String title) {
        dialog.show(fragmentManager, title);
    }

    protected void addFragment(int id, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(id, fragment).addToBackStack(tag);
        fragmentTransaction.commit();
    }

    protected void replaceFragment(int id, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(id, fragment).addToBackStack(tag);
        fragmentTransaction.commit();
    }

    protected void removeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
        getSupportFragmentManager().popBackStack();
    }

    protected void backStackFragments() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}