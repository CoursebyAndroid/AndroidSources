package com.example.korot.rx_login.authActivity.utils;

import android.util.Log;
import com.example.korot.rx_login.app.model.TestRealm;
import com.example.korot.rx_login.app.model.UserRealm;
import com.example.korot.rx_login.app.utils.IRealmService;
import com.example.korot.rx_login.authActivity.sosial.ISocialController;
import com.example.korot.rx_login.basePackage.BasePresenter;
import com.example.korot.rx_login.basePackage.IBaseView;
import com.example.korot.rx_login.basePackage.IInteractorContract;
import com.example.korot.rx_login.basePackage.IPresenterContract;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import java.util.ArrayList;
import javax.inject.Inject;

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
                            realmService.addObject(new UserRealm(next.getmPhone(),next.getmEmail(),next.getmPassword()), UserRealm.class)
                            .doOnError(Throwable::getStackTrace)
                            .subscribe(
                                    t -> {
                                        Log.d("UserRealm ", "RESPONSE Server singUp to Realm ");
                                    },
                                    Throwable::getStackTrace
                            );
                            view.isAuth(next);
                        }, throwable -> {
                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
        realmService.getObject(UserRealm.class)
                .doOnError(Throwable::getStackTrace)
                .subscribe(next ->{
                            Log.d("UserRealm ", "Get Object UserRealm to Realm " + next);
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
                            realmService.addObject(new UserRealm(next.getmEmail(),next.getmPhone()), UserRealm.class)
                            .doOnError(Throwable::getStackTrace)
                            .subscribe(
                                    t -> {
                                        Log.d("UserRealm ", "RESPONSE Server login to Realm ");
                                    },
                                    Throwable::getStackTrace
                            );
                            view.isAuth(next);
                        }, throwable -> {

                            Log.e(TAG, " ERROR " + throwable.getMessage());
                        }

                );
        realmService.getObject(UserRealm.class)
                .doOnError(Throwable::getStackTrace)
                .subscribe(next ->{
                    Log.d("UserRealm ", "Get Object UserRealm to Realm " + next);
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
        testArrayRealm();
    }

    @Override
    public void getData() {

    }

    @Override
    public void resultGoogle(GoogleSignInResult res) {
       socialController.onResultGoogle(res);
    }

    private void testArrayRealm(){
        ArrayList<TestRealm> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = (int)Math.round((Math.random() * 1000000)) + 1;
            int date = (int)Math.round((Math.random() * 1000));
            arr.add(new TestRealm(id,date));
        }
        Log.e("testArrayRealm","arr" + arr.size());
        socialController.onTestArryaRealm(arr);
    }
}
