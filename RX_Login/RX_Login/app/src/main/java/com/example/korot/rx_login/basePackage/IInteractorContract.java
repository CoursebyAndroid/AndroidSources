package com.example.korot.rx_login.basePackage;

import com.example.korot.rx_login.app.model.User;
import com.google.gson.JsonPrimitive;
import rx.Observable;

public interface IInteractorContract {

    interface IAuthInteractor extends IInteractorContract {
        Observable<User> login(String email, String password);
        Observable<User> signUp(String phone, String email, String password);
        Observable<JsonPrimitive> forgotPassword(String email);
    }

}

