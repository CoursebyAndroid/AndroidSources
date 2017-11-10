package com.example.korot.rx_login.basePackage;

import com.example.korot.rx_login.app.model.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.HashMap;
import java.util.List;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * Created by korot on 01.10.2017.
 */

public interface IInteractorContract {

    interface IAuthInteractor extends IInteractorContract {
        Observable<User> login(String email, String password);
        Observable<User> signUp(String phone, String email, String password);
        Observable<JsonPrimitive> forgotPassword(String email);
    }

}

