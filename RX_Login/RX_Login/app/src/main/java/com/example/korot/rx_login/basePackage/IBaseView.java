package com.example.korot.rx_login.basePackage;


import com.example.korot.rx_login.app.model.User;

public interface IBaseView {

    void showProgress();
    void hideProgress();
    void showError(String error);

    interface IAuthView extends IBaseView {
        void isAuth(User user);
        void isFogot(String masage);
    }
}

