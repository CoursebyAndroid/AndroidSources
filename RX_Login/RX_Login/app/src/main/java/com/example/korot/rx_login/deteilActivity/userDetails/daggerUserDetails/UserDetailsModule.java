//package com.example.korot.rx_login.deteilActivity.userDetails.daggerUserDetails;
//
//import com.example.korot.rx_login.authActivity.utils.AuthInteractorImpl;
//import com.example.korot.rx_login.authActivity.utils.IAuthInteractor;
//import com.example.korot.rx_login.deteilActivity.utils.DeteilInteractorImpl;
//import com.example.korot.rx_login.deteilActivity.utils.IDeteilInteractor;
//import com.example.korot.rx_login.authActivity.registFragment.daggerRegist.RegistScope;
//import com.example.korot.rx_login.authActivity.registFragment.utils.AuthRegistPresenterImpl;
//import com.example.korot.rx_login.authActivity.registFragment.utils.IAuthRegistPresenter;
//
//
//import dagger.Module;
//import dagger.Provides;
//
///**
// * Created by korot on 28.09.2017.
// */
//
//@Module
//public class UserDetailsModule {
//
//    private DeteilInteractorImpl detailInteractor;
//
//    public UserDetailsModule(DeteilInteractorImpl detailInteractor) {
//        this.detailInteractor = detailInteractor;
//    }
//    @Provides
//    @RegistScope
//    com.example.korot.rx_login.deteilActivity.userDetails.utils.IDetailsPresenter provideAuthRegistPresenter(IAuthInteractor iAuthInteractor ) {
//        return new AuthRegistPresenterImpl(iAuthInteractor);
//    }
//
//}




