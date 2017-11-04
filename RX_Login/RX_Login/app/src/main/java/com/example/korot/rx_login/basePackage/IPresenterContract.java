package com.example.korot.rx_login.basePackage;

/**
 * Created by korot on 01.10.2017.
 */

public interface IPresenterContract{

    void dismiss();

    interface IAuthPresenter<V extends IBaseView.IAuthView> extends IPresenterContract {
        void login(String email, String password);
        void singUp(String phone, String email,String password);
        void forgotPassword (String email);
        void inSelect(int select);
        void init(V view);
    }
}

