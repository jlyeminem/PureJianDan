package com.jly.purejiandan.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.Picture;
import com.jly.purejiandan.bean.PictureList;
import com.jly.purejiandan.network.RetrofitManager;
import com.jly.purejiandan.ui.LoadOnScollListener;
import com.jly.purejiandan.ui.adapter.PictureAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class PictureFragment extends Fragment {
    @Bind(R.id.rc_view)
    RecyclerView mRcView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private Snackbar mLoadLatestSnackbar;
    private Snackbar mLoadBeforeSnackbar;
    private PictureAdapter mPictureAdapter;
    private LoadOnScollListener mLoadOnScollListener;
    //用来控制当前显示的页数
    private int mCurPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_list, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mCurPage = 1;
        //初始化SwipeRefreshLayout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadLastPictures();
                //刷新最新新鲜事时，页数归一
                mCurPage = 1;
            }
        });

        //初始化RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRcView.setLayoutManager(layoutManager);
        /**
         * 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
         * 用来使RecyclerView保持固定大小，该信息被用于自身的优化
         **/
        mRcView.setHasFixedSize(true);
        mPictureAdapter = new PictureAdapter(getActivity(), new ArrayList<Picture>());
        mRcView.setAdapter(mPictureAdapter);
        mLoadOnScollListener = new LoadOnScollListener(layoutManager, mCurPage) {
            @Override
            public void loadMore(int curPage) {
                loadBeforePictures(++mCurPage);
            }

        };
        mRcView.addOnScrollListener(mLoadOnScollListener);

        if (mPictureAdapter.getPictureList().size() == 0) {
//            mSwipeRefreshLayout.setProgressViewOffset(false, 0,
//                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
//            mSwipeRefreshLayout.setRefreshing(true);
            //解决刷新指示器不显示问题
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
            loadLastPictures();
            //刷新最新新鲜事时，页数归一
            mCurPage = 1;
        }

        mLoadLatestSnackbar = Snackbar.make(mRcView, "加载失败，请重试", Snackbar.LENGTH_INDEFINITE)
                .setAction("刷新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadLastPictures();
                        //刷新最新新鲜事时，页数归一
                        mCurPage = 1;
                    }
                });
        mLoadBeforeSnackbar = Snackbar.make(mRcView, "加载更多失败，请重试", Snackbar.LENGTH_INDEFINITE)
                .setAction("刷新", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadBeforePictures(++mCurPage);
                    }
                });
    }

    private void loadBeforePictures(int page) {
        RetrofitManager.builder().getBeforePictures(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PictureList>() {
                    @Override
                    public void call(PictureList PictureList) {
                        mPictureAdapter.addData(PictureList.getComments());
                        mLoadOnScollListener.setLoading(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoadOnScollListener.setLoading(false);
                        mLoadBeforeSnackbar.show();
                    }
                });
    }

    private void loadLastPictures() {
        RetrofitManager.builder().getLatestPictures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<PictureList>() {
                    @Override
                    public void call(PictureList PictureList) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mPictureAdapter.changeData(PictureList.getComments());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mLoadLatestSnackbar.show();
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
