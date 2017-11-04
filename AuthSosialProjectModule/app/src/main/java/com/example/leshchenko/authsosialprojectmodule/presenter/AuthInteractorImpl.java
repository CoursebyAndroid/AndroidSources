package com.example.leshchenko.authsosialprojectmodule.presenter;

import com.example.leshchenko.authsosialprojectmodule.config.ConfigDao;
import com.example.leshchenko.authsosialprojectmodule.model.UserRealm;
import com.example.leshchenko.authsosialprojectmodule.util.IApiServise;
import com.google.gson.JsonPrimitive;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Root on 31.10.2017.
 */

public class AuthInteractorImpl implements IAuthInteractor{

    private IApiServise api;

    public AuthInteractorImpl(IApiServise api) {
        this.api = api;
    }

    @Override
    public Observable<UserRealm> signUp(String phone, String email, String password) {
        return api.signUp(ConfigDao.COMMAND_REG_MIN, email, phone, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Observable<JsonPrimitive> forgotPassword(String email) {
        return api.forgotPassword(ConfigDao.COMMAND_FORGOT_PASSWORD, email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }


    @Override
    public Observable<UserRealm> login(String email, String password) {
        return api.login(ConfigDao.COMMAND_AUTH, email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }
}
