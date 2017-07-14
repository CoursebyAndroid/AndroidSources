package com.leshchenko.root.lindiaprojectx.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.leshchenko.root.lindiaprojectx.R;

/**
 * Created by Root on 09.07.2017.
 */

public class OrderHolder extends RecyclerView.ViewHolder {

    private TextView mNameTextView;
    private TextView mDescriptionTextView;
    private TextView mPriceTextView;

    public OrderHolder(View itemView) {
        super(itemView);
        mNameTextView = (TextView) itemView.findViewById(R.id.name_view_orders);
        mDescriptionTextView = (TextView) itemView.findViewById(R.id.description_view_orders);
        mPriceTextView = (TextView) itemView.findViewById(R.id.price_view_orders);
    }

    public TextView getmNameTextView() {
        return mNameTextView;
    }

    public TextView getmDescriptionTextView() {
        return mDescriptionTextView;
    }

    public TextView getmPriceTextView() {
        return mPriceTextView;
    }
}
