package com.example.leshchenko.authsosialprojectmodule.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.leshchenko.authsosialprojectmodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LoginFragment extends Fragment {
    public static final String TAG = "LoginFragment";
    private OnFragmentInteractionListener mListener;

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
    public LoginFragment() {

    }
    private ILogin mILogin;

    public interface ILogin{
        void onLoginClickListener(String email, String pass);
        void onRegisterClickListener();
        void onForgotPassClickListener();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
