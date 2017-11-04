package com.example.korot.rx_login.authActivity.loginFragment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.example.korot.rx_login.R;
import com.example.korot.rx_login.basePackage.BaseFragment;
import com.example.korot.rx_login.basePackage.IOnBackPressed;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends BaseFragment implements IOnBackPressed {

    private static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.et_login_email)
    AppCompatEditText mEtEmail;

    @BindView(R.id.et_login_password)
    AppCompatEditText mEtPassword;

    @BindView(R.id. btn_login_register)
    AppCompatButton mBtnRegister;

    @BindView(R.id.btn_login_forgot_pass)
    AppCompatButton mBtnForgotPass;

    @BindView(R.id.btn_login)
    AppCompatButton mBtnLoginIn;

    @BindView(R.id.progress_layout_login)
    RelativeLayout mCommonLayout;

    @BindView(R.id.progress_login)
    ProgressBar mProgressBar;

    @BindView(R.id.btn_facebook)
    AppCompatButton mBtnFacebook;

    @BindView(R.id.btn_google)
    AppCompatButton mBtnGoogle;

    @BindView(R.id.btn_instagram)
    AppCompatButton mBtnInstagram;

    @BindView(R.id.btn_twitter)
    AppCompatButton mBtnTwitter;

    public ILogin mILogin;


    public FragmentManager fragmentManager;

    public LoginFragment( ) {}

    @Override
    public void onBack() {
        fragmentManager.popBackStack();
    }


    public interface ILogin{
        void onLoginClickListener(String email, String pass);
        void onRegisterClickListener();
        void onForgotPassClickListener();
        void onFacebookClickListener();
        void onTwitterClickListener();
        void onGoogleClickListener();
        void onInstagramClickListener();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Log.e(TAG, " newInstance()");
        return fragment;
    }

    @Override
    protected <T> T getComponent(Class<T> componentType) {

        return super.getComponent(componentType);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewLogin = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, viewLogin);
        Log.d(TAG, " onCreateView()");
        return viewLogin;
    }

    @OnClick(R.id. btn_login_register)
    public void onRegisterClick(){
        Log.e(TAG, "onRegisterClick Pressed()");
        LoginFragment.this.mILogin.onRegisterClickListener();
    }

    @OnClick(R.id.btn_login_forgot_pass)
    public void onFogotClick(){
        Log.e(TAG, "ForgotPassword Pressed()");
        LoginFragment.this.mILogin.onForgotPassClickListener();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick(){
        Log.e(TAG, " onLoginClick() " + " email - " + mEtEmail.getText().toString() + " password - " + mEtPassword.getText().toString());
        LoginFragment.this.mILogin.onLoginClickListener(mEtEmail.getText().toString(), mEtPassword.getText().toString());
    }

    @OnClick(R.id.btn_facebook)
    public void onFacebookClick(){
        Log.e(TAG, "onFacebookClick Pressed()");
        LoginFragment.this.mILogin.onFacebookClickListener();
    }

    @OnClick(R.id.btn_google)
    public void onGoogleClick(){
        Log.e(TAG, "onGoogleClick Pressed()");
        LoginFragment.this.mILogin.onGoogleClickListener();
    }
    @OnClick(R.id.btn_twitter)
    public void onTwitterClick(){
        Log.e(TAG, "onTwitterClick Pressed()");
        LoginFragment.this.mILogin.onTwitterClickListener();
    }

    @OnClick(R.id.btn_instagram)
    public void onInstagramClick(){
        Log.e(TAG, "onInstagramClick Pressed()");
        LoginFragment.this.mILogin.onInstagramClickListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, " onAttach()");
        if(context instanceof ILogin){
            mILogin = (ILogin) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement ILogin");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, " onDetach()");
        mILogin = null;
    }
}