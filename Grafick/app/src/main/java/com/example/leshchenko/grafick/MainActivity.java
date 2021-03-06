package com.example.leshchenko.grafick;


import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentActivity mFragment = new FragmentActivity();
        fragmentTransaction.add(R.id.activity_main, mFragment);
        fragmentTransaction.commit();
    }
}
