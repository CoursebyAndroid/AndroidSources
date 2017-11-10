package com.example.korot.rx_login.authActivity.fogotPassFragment.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.example.korot.rx_login.R;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgotPassDialog extends DialogFragment {

    private static final String TAG = ForgotPassDialog.class.getSimpleName();

    @BindView(R.id.progress_forgot_pass)
    ProgressBar mProgressBar;

    @BindView(R.id.progress_layout_login)
    RelativeLayout mCommonLayout;

    @BindView(R.id.et_forgot_pass_email)
    EditText mPutEmail;

    @BindView(R.id.btn_forgot_pass_cancel)
    AppCompatButton mBtnCancel;

    @BindView(R.id.btn_forgot_pass_ok)
    AppCompatButton mBtnOk;

    @BindDrawable(android.R.drawable.presence_online)
    Drawable mValidField;

    @BindDrawable(android.R.drawable.presence_busy)
    Drawable mInvalidField;

    public IFogot mIFogot;

    public interface IFogot{
        void onFogotClickListener(String email);
    }
    public static ForgotPassDialog newInstance() {
        ForgotPassDialog dialog = new ForgotPassDialog();
        Log.e(TAG,"newInstance");
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.fragment_fogot_pass, null);
        ButterKnife.bind(this, dialogView);
        Log.e(TAG,"onCreateView");
        return dialogView;
    }

    @OnClick(R.id.btn_forgot_pass_cancel)
    public void onDialogCancel(){
        Log.e(TAG,"onDialogCancel");
        this.dismiss();
    }

    @OnClick(R.id.btn_forgot_pass_ok)
    public void onDialogOk(){
        Log.e(TAG, " onDialogOk() " + " email - " + mPutEmail.getText().toString());
        mIFogot.onFogotClickListener(mPutEmail.getText().toString());
        this.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, " onAttach()");
        if (context instanceof IFogot) {
            mIFogot = (IFogot) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IForgotPass");
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.e(TAG, " onDismiss() ");
        mIFogot = null;

    }

    public void showLoading() {
        mCommonLayout.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }


    public void hideLoading() {
        mCommonLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        this.dismiss();
    }
}
