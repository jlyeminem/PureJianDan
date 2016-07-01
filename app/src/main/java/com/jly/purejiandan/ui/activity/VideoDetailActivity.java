package com.jly.purejiandan.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.jly.purejiandan.R;
import com.jly.purejiandan.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/11.
 */
public class VideoDetailActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.imgBtn_back)
    ImageButton mImgBtnBack;
    @Bind(R.id.imgBtn_forward)
    ImageButton mImgBtnForward;
    @Bind(R.id.imgBtn_control)
    ImageButton mImgBtnControl;
    @Bind(R.id.webview)
    WebView mWebview;

    private String url;

    private boolean isLoadFinish = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        ButterKnife.bind(this);
        initView();
        url = getIntent().getStringExtra("url");
        mWebview.loadUrl(url);
    }

    @Override
    protected void initView() {
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(R.string.loading);
        mToolbar.setNavigationIcon(R.drawable.ic_actionbar_back);

        mImgBtnBack.setOnClickListener(this);
        mImgBtnForward.setOnClickListener(this);
        mImgBtnControl.setOnClickListener(this);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(
                new WebChromeClient() {
                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {

                        if (newProgress == 100) {
                            mProgress.setVisibility(View.GONE);
                        } else {
                            mProgress.setProgress(newProgress);
                            mProgress.setVisibility(View.VISIBLE);
                        }

                        super.onProgressChanged(view, newProgress);
                    }
                }

        );
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mImgBtnControl.setImageResource(R.drawable.ic_action_refresh);
                isLoadFinish = true;
                mToolbar.setTitle(view.getTitle());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtn_back:
                if (mWebview.canGoBack()) {
                    mWebview.goBack();
                }
                break;
            case R.id.imgBtn_forward:
                if (mWebview.canGoForward()) {
                    mWebview.goForward();
                }
                break;
            case R.id.imgBtn_control:

                if (isLoadFinish) {
                    mWebview.reload();
                    isLoadFinish = false;
                } else {
                    mWebview.stopLoading();
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mWebview != null) {
            mWebview.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebview != null) {
            mWebview.onPause();
        }
    }
}
