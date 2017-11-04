package com.example.leshchenko.authsosialprojectmodule.presenter;

/**
 * Created by Root on 31.10.2017.
 */

public interface IAuthPresenter {
     void singUp(String phone, String email, String password);
     void login(String email, String password);
    void forgotPassword(String email);


}
