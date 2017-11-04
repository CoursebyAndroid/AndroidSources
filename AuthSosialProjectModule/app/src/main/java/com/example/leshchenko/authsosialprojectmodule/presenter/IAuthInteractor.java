package com.example.leshchenko.authsosialprojectmodule.presenter;

import com.example.leshchenko.authsosialprojectmodule.model.UserRealm;
import com.google.gson.JsonPrimitive;

import rx.Observable;

/**
 * Created by Root on 31.10.2017.
 */

public interface IAuthInteractor {
    Observable<UserRealm> signUp(String phone, String email, String password);
    Observable<JsonPrimitive> forgotPassword(String email);
    Observable<UserRealm> login(String email, String password);
}
