package com.jly.purejiandan.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.FreshNews;
import com.jly.purejiandan.bean.FreshNewsDetail;
import com.jly.purejiandan.network.RetrofitManager;
import com.jly.purejiandan.ui.activity.CommentsActivity;
import com.jly.purejiandan.ui.activity.FreshDetailActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class FreshDetailFragment extends Fragment {

    @Bind(R.id.iv_header)
    ImageView mIvHeader;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_source)
    TextView mTvSource;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.wv_news)
    WebView mWvNews;
//    @Bind(R.id.nested_view)
//    NestedScrollView mNestedView;
    @Bind(R.id.tv_load_error)
    TextView mTvLoadError;
    @Bind(R.id.pb_loading)
    ContentLoadingProgressBar mPbLoading;

    private FreshNews mFreshNews;
    private int mId;

    public static FreshDetailFragment newInstance(FreshNews news) {
        FreshDetailFragment fragment = new FreshDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(FreshDetailActivity.KEY_FRESH, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFreshNews = getArguments().getParcelable(FreshDetailActivity.KEY_FRESH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fresh_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        loadFreshDetail(mId);
        return view;
    }

    private void loadFreshDetail(int id) {
        RetrofitManager.builder().getFreshNewsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mTvLoadError.setVisibility(View.GONE);
                        mPbLoading.show();
                    }
                })
                .subscribe(new Action1<FreshNewsDetail>() {
                    @Override
                    public void call(FreshNewsDetail freshNewsDetail) {
                        mPbLoading.hide();
                        mTvLoadError.setVisibility(View.GONE);
                        if (freshNewsDetail != null) {
                            mWvNews.loadDataWithBaseURL("", getHtml(mFreshNews, freshNewsDetail.getPost().getContent()), "text/html", "utf-8", "");
                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mPbLoading.hide();
                        mTvLoadError.setVisibility(View.VISIBLE);
                    }
                });

    }

    private void initView() {
        //初始化标题栏
        setHasOptionsMenu(true);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mToolbar.setTitle("新鲜事");
        //初始化已知UI
        if (mFreshNews != null) {
            if(mFreshNews.getCustom_fields()!=null) {
                List<String> images = mFreshNews.getCustom_fields().getThumb_c();
                if (images != null && images.size() > 0) {
                    Glide.with(getActivity()).load(images.get(0)).placeholder(R.drawable.ic_loading_small).into(mIvHeader);
                }
            }
            mTvTitle.setText(mFreshNews.getTitle());
            String strSource =mFreshNews.getAuthor().getName();
                    //+"@"+mFreshNews.getTags().get(0).getTitle();
            mTvSource.setText(strSource);
            mId = mFreshNews.getId();
        }
        mWvNews.getSettings().setJavaScriptEnabled(true);

        mTvLoadError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFreshDetail(mId);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fresh_news_detail,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_comment:
                Intent i = new Intent(getActivity(), CommentsActivity.class);
                i.putExtra(CommentsActivity.ID_FRESH,mFreshNews.getId());
                getActivity().startActivity(i);
                return true;
            case R.id.action_share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        mFreshNews.getTitle() + " " + mFreshNews.getUrl());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(Intent.createChooser(intent, getActivity().getResources().getString(R
                        .string.app_name)));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    private static String getHtml(FreshNews freshNews, String content) {
        final StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/style.css' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("</div>");
        sb.append("<div class=\"entry\">");
        sb.append(content);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}
