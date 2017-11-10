package com.example.korot.rx_login.basePackage;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public interface IPresenterContract{

    void dismiss();

    interface IAuthPresenter<V extends IBaseView.IAuthView> extends IPresenterContract {
        void login(String email, String password);
        void singUp(String phone, String email,String password);
        void forgotPassword (String email);
        void inSelect(int select);
        void getData();
        void init(V view);
    }

    interface IAuthPresenterSosial extends IPresenterContract {
        void resultGoogle(GoogleSignInResult res);
    }
}

