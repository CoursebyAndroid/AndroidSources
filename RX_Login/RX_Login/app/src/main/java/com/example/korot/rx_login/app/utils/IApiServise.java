package com.example.korot.rx_login.app.utils;

import com.example.korot.rx_login.app.config.Config;
import com.example.korot.rx_login.app.model.User;
import com.google.gson.JsonPrimitive;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;


public interface IApiServise {
    @FormUrlEncoded
    @POST(Config.URL_CONTROLLER_AUTH)
    Observable<User> signUp(
            @Field("command") String command,
            @Field("email") String mail,
            @Field("phone") String phone,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(Config.URL_CONTROLLER_AUTH)
    Observable<User> login(
            @Field("command") String command,
            @Field("email") String mail,
            @Field("password") String password);

    @FormUrlEncoded
    @POST(Config.URL_CONTROLLER_AUTH)
    Observable<JsonPrimitive> forgotPassword(
            @Field("command") String command,
            @Field("email") String mail);

}
