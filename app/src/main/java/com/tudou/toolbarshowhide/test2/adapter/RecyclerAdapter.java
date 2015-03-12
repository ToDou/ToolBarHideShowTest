package com.tudou.toolbarshowhide.test2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tudou.toolbarshowhide.R;
import com.tudou.toolbarshowhide.test2.adapter.viewholder.RecyclerHeaderViewHolder;
import com.tudou.toolbarshowhide.test2.adapter.viewholder.RecyclerItemViewHolder;

import java.util.List;

/**
 * Created by tudou on 15-3-11.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<String> mItemList;

    public RecyclerAdapter(List<String> itemList) {
        mItemList = itemList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        if (viewType == TYPE_ITEM) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
            return RecyclerItemViewHolder.newInstance(view);
        } else if(viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, viewGroup, false);
            return new RecyclerHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types    correctly");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (!isPositionHeader(position)) {
            RecyclerItemViewHolder holder = (RecyclerItemViewHolder) viewHolder;
            String itemText = mItemList.get(position - 1);
            holder.setItemText(itemText);
        }
    }

    private int getBasicItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }

    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
