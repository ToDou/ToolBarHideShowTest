package com.tudou.toolbarshowhide.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tudou.toolbarshowhide.R;

/**
 * Created by tudou on 15-3-11.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder{

    private final TextView mItemTextView;

    public RecyclerItemViewHolder(View itemView, TextView itemTextView) {
        super(itemView);
        mItemTextView = itemTextView;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new RecyclerItemViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }
}
