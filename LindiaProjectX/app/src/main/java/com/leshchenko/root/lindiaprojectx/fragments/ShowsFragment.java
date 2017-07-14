package com.leshchenko.root.lindiaprojectx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leshchenko.root.lindiaprojectx.R;

public class ShowsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ShowsFragment() {
        // Required empty public constructor
    }

    public static ShowsFragment newInstance() {
        ShowsFragment fragment = new ShowsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View viewShows = inflater.inflate(R.layout.fragment_shows, container, false);
        return viewShows;
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
