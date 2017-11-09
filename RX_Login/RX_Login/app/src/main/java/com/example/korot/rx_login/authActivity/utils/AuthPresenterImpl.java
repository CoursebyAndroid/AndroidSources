package com.example.korot.rx_login.authActivity.utils;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.korot.rx_login.R;
import com.example.korot.rx_login.app.utils.IApiServise;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.app.utils.RealmService;
import com.example.korot.rx_login.authActivity.sosial.ISocialController;
import com.example.korot.rx_login.authActivity.sosial.SocialControllerImpl;
import com.example.korot.rx_login.authActivity.ui.AuthActivity;
import com.example.korot.rx_login.basePackage.BaseFragment;
import com.example.korot.rx_login.basePackage.BasePresenter;
import com.example.korot.rx_login.basePackage.IBaseView;
import com.example.korot.rx_login.basePackage.IInteractorContract;
import com.example.korot.rx_login.basePackage.IPresenterContract;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;


public class AuthPresenterImpl extends BasePresenter<IBaseView.IAuthView, IInteractorContract.IAuthInteractor>
        implements IPresenterContract.IAuthPresenter, IPresenterContract.IAuthPresenterSosial {

    private static final String TAG = AuthPresenterImpl.class.getSimpleName();


    @Inject
    public AuthPresenterImpl(IInteractorContract.IAuthInteractor authInteractor, IRealmService realmService, ISocialController iSocialController ) {
        this.mAuthInteractor = authInteractor;
        this.realmService = realmService;
        this.socialController = iSocialController;
    }


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

    @Override
    public void getData() {

    }

    @Override
    public void resultGoogle(GoogleSignInResult res) {
       socialController.onResultGoogle(res);
    }
}
