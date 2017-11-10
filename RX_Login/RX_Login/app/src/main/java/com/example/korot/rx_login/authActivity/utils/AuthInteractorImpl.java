package com.example.korot.rx_login.authActivity.utils;

import com.example.korot.rx_login.app.config.Config;
import com.example.korot.rx_login.app.model.User;
import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.basePackage.IInteractorContract;
import com.google.gson.JsonPrimitive;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AuthInteractorImpl implements IInteractorContract.IAuthInteractor {

    private IApiServise api;

    public AuthInteractorImpl(IApiServise api) {
        this.api = api;
    }

    @Override
    public Observable<User> signUp(String phone, String email, String password) {
        return api.signUp(Config.COMMAND_REG_MIN, email, phone, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Observable<JsonPrimitive> forgotPassword(String email) {
        return api.forgotPassword(Config.COMMAND_FORGOT_PASSWORD, email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }


    @Override
    public Observable<User> login(String email, String password) {
        return api.login(Config.COMMAND_AUTH, email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnError(Throwable::printStackTrace);
    }

}
