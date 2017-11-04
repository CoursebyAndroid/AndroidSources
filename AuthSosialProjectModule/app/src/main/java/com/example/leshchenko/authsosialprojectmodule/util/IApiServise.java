package com.example.leshchenko.authsosialprojectmodule.util;


import com.example.leshchenko.authsosialprojectmodule.config.ConfigDao;
import com.example.leshchenko.authsosialprojectmodule.model.UserRealm;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by korot on 14.08.2017.
 */

public interface IApiServise {
    @FormUrlEncoded
    @POST(ConfigDao.URL_CONTROLLER_AUTH)
    Observable<UserRealm> signUp(
            @Field("command") String command,
            @Field("email") String mail,
            @Field("phone") String phone,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(ConfigDao.URL_CONTROLLER_AUTH)
    Observable<UserRealm> login(
            @Field("command") String command,
            @Field("email") String mail,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(ConfigDao.URL_CONTROLLER_AUTH)
    Observable<JsonPrimitive> forgotPassword(
            @Field("command") String command,
            @Field("email") String mail);
}
