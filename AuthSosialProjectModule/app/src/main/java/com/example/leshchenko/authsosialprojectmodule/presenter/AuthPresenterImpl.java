package com.example.leshchenko.authsosialprojectmodule.presenter;

import android.util.Log;

import com.example.leshchenko.authsosialprojectmodule.dao.IRealmService;

/**
 * Created by Root on 31.10.2017.
 */

public class AuthPresenterImpl implements IAuthPresenter {
    IRealmService realmService;
    public static final String TAG = AuthPresenterImpl.class.getSimpleName();
    @Override
    public void singUp(String phone, String email, String password) {
        mAuthInteractor.signUp(phone, email, password)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            view.isAuth(next);
                        }, throwable -> {
                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
    }

    @Override
    public void login(String email, String password) {
        mAuthInteractor.login(email, password)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            view.isAuth(next);
                        }, throwable -> {

                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
    }

    @Override
    public void forgotPassword(String email) {
        Log.e(TAG, "forgotPassword");
        mAuthInteractor.forgotPassword(email)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            view.isFogot(next.toString());
                        }, throwable -> {
                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }
                );
    }
}
