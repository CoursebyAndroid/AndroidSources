package com.example.leshchenko.authsosialprojectmodule.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.leshchenko.authsosialprojectmodule.R;
import com.example.leshchenko.authsosialprojectmodule.modules_app.App;
import com.example.leshchenko.authsosialprojectmodule.modules_app.AppComponent;
import com.example.leshchenko.authsosialprojectmodule.modules_app.SplashModule;

import butterknife.Unbinder;

/**
 * Created by Root on 02.11.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected FragmentManager fragmentManager;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_auth);
        fragmentManager = getSupportFragmentManager();
        setupComponent(App.get(this).getAppComponent());
    }

    protected abstract void setupComponent(AppComponent appComponent);

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
