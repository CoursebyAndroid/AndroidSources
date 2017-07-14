package com.leshchenko.root.lindiaprojectx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leshchenko.root.lindiaprojectx.R;


public class MyAccountFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public MyAccountFragment() {
        // Required empty public constructor
    }


    public static MyAccountFragment newInstance() {
        MyAccountFragment fragment = new MyAccountFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View viewAccount = inflater.inflate(R.layout.fragment_my_account, container, false);
        return viewAccount;
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
