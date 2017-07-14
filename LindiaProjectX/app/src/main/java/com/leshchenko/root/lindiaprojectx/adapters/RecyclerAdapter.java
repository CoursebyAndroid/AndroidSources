package com.leshchenko.root.lindiaprojectx.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leshchenko.root.lindiaprojectx.R;
import com.leshchenko.root.lindiaprojectx.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Root on 09.07.2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<OrderHolder> {

    private ArrayList<Order>listOrder;
    private Context mContext;


    private static RecyclerView.Adapter adapter;

    private static final String TAG = "RecyclerAdapter";

    public RecyclerAdapter(Context mContext,ArrayList<Order> listOrder)
    {
        Log.d(TAG,"Конструктор RecyclerAdapter");
        this.mContext = mContext;
        this.listOrder = listOrder;

    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG,"onCreateViewHolder RecyclerAdapter");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_orders,parent,false);
        OrderHolder orderHolder = new OrderHolder(view);
        return orderHolder;
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position)
    {
        Log.d(TAG,"onBindViewHolder RecyclerAdapter");
        Order order = listOrder.get(position);
        holder.getmNameTextView().setText(order.getName());
        holder.getmDescriptionTextView().setText(order.getDescription());
        holder.getmPriceTextView().setText(String.valueOf(order.getPrice()));
    }


    @Override
    public int getItemCount() {
        Log.d(TAG,"getItemCount RecyclerAdapter");

        return listOrder.size();
    }
}