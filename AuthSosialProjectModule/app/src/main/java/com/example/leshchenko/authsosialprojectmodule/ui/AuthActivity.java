package com.example.leshchenko.authsosialprojectmodule.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.leshchenko.authsosialprojectmodule.modules_app.App;
import com.example.leshchenko.authsosialprojectmodule.modules_app.AppComponent;
import com.example.leshchenko.authsosialprojectmodule.modules_app.SplashModule;
import com.example.leshchenko.authsosialprojectmodule.R;
import com.example.leshchenko.authsosialprojectmodule.ui.fragment.LoginFragment;

import butterknife.ButterKnife;


public class AuthActivity extends BaseActivity {
    public static final String TAG = "AuthActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        addFragment(R.id.auth,LoginFragment.newInstance(),TAG);
    }


    protected void setupComponent(AppComponent appComponent) {
        appComponent.plus(new SplashModule(this)).inject(this);
    }
}
