package com.example.korot.rx_login.authActivity.registFragment.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.korot.rx_login.R;
import com.example.korot.rx_login.authActivity.loginFragment.ui.LoginFragment;
import com.example.korot.rx_login.basePackage.IOnBackPressed;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegistrationFragment extends Fragment implements IOnBackPressed {


    @BindView(R.id.et_register_email)
    EditText mEtRegisterEmail;

    @BindView(R.id.id_et_password)
    EditText mEtRegisterPassword;

    @BindView(R.id.et_register_phone)
    EditText mEtRegisterPhone;

    @BindView(R.id. btn_register)
    AppCompatButton mBtnRegister;

    @BindView(R.id.progress_register)
    ProgressBar mProgressBar;

    private static final String TAG = LoginFragment.class.getSimpleName();

    public IRegist mIRegist;

    public FragmentManager fragmentManager;

    public RegistrationFragment() {
    }

    @Override
    public void onBack() {
        fragmentManager.popBackStack();
    }

    public interface IRegist{
        void onRegistClickListener(String phone,String email, String pass);
    }

    public static RegistrationFragment newInstance() {
        Log.e(TAG,"newInstance");
        RegistrationFragment fragment = new RegistrationFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"onCreateView");
        View v = inflater.inflate(R.layout.fragment_registration, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClick(){
        Log.e(TAG, " onLoginClick() " +
                " phone - " +mEtRegisterPhone.getText().toString() +
                " email - " + mEtRegisterEmail.getText().toString() +
                " password - " + mEtRegisterPassword.getText().toString());
        RegistrationFragment.this.mIRegist.onRegistClickListener(mEtRegisterPhone.getText().toString(),
                mEtRegisterEmail.getText().toString(),mEtRegisterPassword.getText().toString());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, " onAttach()");
        if(context instanceof IRegist){
            mIRegist = (IRegist) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IRegister");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, " onDetach()");
        mIRegist = null;
    }
}
