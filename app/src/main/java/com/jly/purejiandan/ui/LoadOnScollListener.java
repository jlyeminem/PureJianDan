package com.jly.purejiandan.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by jly on 2016/6/6.
 */
public abstract class LoadOnScollListener extends RecyclerView.OnScrollListener{
    private LinearLayoutManager mLinearLayoutManager;
    private int mCurPage;
    public LoadOnScollListener(LinearLayoutManager linearLayoutManager, int curPage){
        mLinearLayoutManager = linearLayoutManager;
        mCurPage = curPage;
    }
    private boolean loading = false;

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalNumOfItems = mLinearLayoutManager.getItemCount();
        int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (!loading && (lastVisibleItem > totalNumOfItems - 3) && dy > 0) {
            loadMore(mCurPage);
            loading = true;
        }
    }

    public int getCurPage() {
        return mCurPage;
    }

    public void setCurPage(int curPage) {
        mCurPage = curPage;
    }

    public abstract void loadMore(int curPage);
}
