package com.tudou.toolbarshowhide.test2.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.tudou.toolbarshowhide.test2.Utils;

/**
 * Created by tudou on 15-3-12.
 */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private static final float HIDE_THRESHOLD = 10;
    private static final float SHOW_THRESHOLD = 70;

    private int mToolbarOffset = 0;
    private int mToolbarHeight;
    private boolean mControlVisible = true;
    private int mTotalScrolledDistance;

    protected HidingScrollListener(Context context) {
        mToolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if(mTotalScrolledDistance < mToolbarHeight) {
                setVisible();
            } else {
                if (mControlVisible) {
                    if (mToolbarOffset > HIDE_THRESHOLD) {
                        setInvisible();
                    } else {
                        setVisible();
                    }
                } else {
                    if ((mToolbarHeight - mToolbarOffset) > SHOW_THRESHOLD) {
                        setVisible();
                    } else {
                        setInvisible();
                    }
                }
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolBarOffset();
        onMoved(mToolbarOffset);

        if((mToolbarOffset <mToolbarHeight && dy>0) || (mToolbarOffset >0 && dy<0)) {
            mToolbarOffset += dy;
        }
        if (mTotalScrolledDistance < 0) {
            mTotalScrolledDistance = 0;
        } else {
            mTotalScrolledDistance += dy;
        }
    }

    private void clipToolBarOffset() {
        if (mToolbarOffset > mToolbarHeight) {
            mToolbarOffset = mToolbarHeight;
        } else if (mToolbarOffset < 0) {
            mToolbarOffset = 0;
        }
    }

    private void setVisible() {
        if (mToolbarOffset > 0) {
            onShow();
            mToolbarOffset = 0;
        }
        mControlVisible = true;
    }

    private void setInvisible() {
        if (mToolbarOffset < mToolbarHeight) {
            onHide();
            mToolbarOffset = mToolbarHeight;
        }
        mControlVisible = false;
    }

    public abstract void onMoved(int distance);
    public abstract void onShow();
    public abstract void onHide();

}
