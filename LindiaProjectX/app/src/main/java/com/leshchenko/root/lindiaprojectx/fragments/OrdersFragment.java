package com.leshchenko.root.lindiaprojectx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leshchenko.root.lindiaprojectx.R;
import com.leshchenko.root.lindiaprojectx.adapters.RecyclerAdapter;
import com.leshchenko.root.lindiaprojectx.model.Order;

import java.util.ArrayList;
import java.util.List;


public class OrdersFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static RecyclerView mRecyclerView;
    private ArrayList<Order> listOrder;
    private RecyclerAdapter recyclerAdapter;

    public OrdersFragment() {}

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewOrder = inflater.inflate(R.layout.fragment_orders, container, false);
        mRecyclerView = (RecyclerView) viewOrder.findViewById(R.id.rv);
        listOrder = recordingToAnArray();
        setupRecycler(listOrder);
        return viewOrder;
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

    private void setupRecycler(ArrayList<Order> list) {
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getContext(), list);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    public ArrayList<Order> recordingToAnArray(){
       // Log.d(TAG,"создание массива");
       ArrayList<Order> listOrder = new ArrayList<>();
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));
        listOrder.add(new Order("Паркинг","Парковка",300));

        return listOrder;
    }
}
