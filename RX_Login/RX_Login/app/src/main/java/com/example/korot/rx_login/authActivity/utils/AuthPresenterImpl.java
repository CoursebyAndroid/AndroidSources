package com.example.korot.rx_login.authActivity.utils;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.app.utils.RealmService;
import com.example.korot.rx_login.authActivity.sosial.SocialControllerImpl;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.example.korot.rx_login.basePackage.BasePresenter;
import com.example.korot.rx_login.basePackage.IBaseView;
import com.example.korot.rx_login.basePackage.IInteractorContract;
import com.example.korot.rx_login.basePackage.IPresenterContract;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by korot on 24.09.2017.
 */

public class AuthPresenterImpl extends BasePresenter<IBaseView.IAuthView, IInteractorContract.IAuthInteractor>
        implements IPresenterContract.IAuthPresenter {

    private static final String TAG = AuthPresenterImpl.class.getSimpleName();
    AuthActivity activity;
    IInteractorContract.IAuthInteractor mAuthInteractor;
    IRealmService realmService;

    @Inject
    public AuthPresenterImpl(IInteractorContract.IAuthInteractor authInteractor, IRealmService realmService) {
        this.mAuthInteractor = authInteractor;
        this.realmService = realmService;
    }
    @Inject
    SocialControllerImpl socialController;

    @Override
    public void singUp(String phone, String email, String password) {
        mAuthInteractor.signUp(phone, email, password)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            //insert to realm
                            // get from realm
                            view.isAuth(next);
                        }, throwable -> {
                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
    }

    @Override
    public void login(String email, String password) {
        mAuthInteractor.login(email, password)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            //insert to realm
                            // get from realm
                            view.isAuth(next);
                        }, throwable -> {

                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
    }

    @Override
    public void forgotPassword(String email) {
        Log.e(TAG, "forgotPassword");
        mAuthInteractor.forgotPassword(email)
                .subscribe(next -> {
                            Log.e(TAG, " RESPONSE " + next);
                            //insert to realm
                            // get from realm
                    view.isFogot(next.toString());
                        }, throwable -> {
                    Log.e(TAG, " ERROR " + throwable.getMessage());
                            }
                );
    }


    public void inSelect(int select) {
        socialController.onSelect(select);
    }
}
