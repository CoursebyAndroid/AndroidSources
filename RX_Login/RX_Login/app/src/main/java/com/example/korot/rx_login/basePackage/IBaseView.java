package com.example.korot.rx_login.basePackage;

import android.widget.Filter;
import android.widget.Gallery;

import com.example.korot.rx_login.app.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;

/**
 * Created by korot on 01.10.2017.
 */

public interface IBaseView {

    void showProgress();
    void hideProgress();
    void showError(String error);

    interface IAuthView extends IBaseView {
        void isAuth(User user);
        void isFogot(String masage);
    }
}

