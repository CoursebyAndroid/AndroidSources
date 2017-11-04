package com.example.korot.rx_login.basePackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.korot.rx_login.app.daggerApp.AppComponent;
import com.example.korot.rx_login.app.daggerApp.MyApp;

import butterknife.Unbinder;

/**
 * Created by korot on 26.08.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected FragmentManager fragmentManager;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(MyApp.get(this).getAppComponent());
        fragmentManager = getSupportFragmentManager();
        initView();
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