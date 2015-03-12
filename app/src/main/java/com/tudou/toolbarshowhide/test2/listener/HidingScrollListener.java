package com.tudou.toolbarshowhide.test2.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.tudou.toolbarshowhide.test2.Utils;

/**
 * Created by tudou on 15-3-12.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private int mToolbarOffset = 0;
    private int mToolbarHeight;

    protected HidingScrollListener(Context context) {
        mToolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolBarOffset();
        onMoved(mToolbarOffset);

        if (mToolbarOffset < mToolbarHeight && dy > 0 || mToolbarOffset > 0 && dy < 0) {
            mToolbarOffset += dy;
        }
    }

    private void clipToolBarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    public abstract void onMoved(int distance);

}
