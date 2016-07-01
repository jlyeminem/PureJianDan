package com.jly.purejiandan.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.jly.purejiandan.R;
import com.jly.purejiandan.utils.FileUtil;
import com.jly.purejiandan.utils.swipeback.SwipeBackActivity;
import com.jly.purejiandan.utils.swipeback.SwipeBackLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by jly on 2016/6/10.
 */
public class PictureDetailActivity extends SwipeBackActivity {
    @Bind(R.id.web_gif)
    WebView mWebGif;
    @Bind(R.id.img)
    PhotoView mImg;
    @Bind(R.id.img_back)
    ImageButton mImgBack;
    @Bind(R.id.img_share)
    ImageButton mImgShare;
    @Bind(R.id.rl_top_bar)
    RelativeLayout mRlTopBar;
    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.fab_save)
    FloatingActionButton mFabSave;

    public static String IMG_URL = "img_url";
    private Context mContext;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);
        mContext = this;
        ButterKnife.bind(this);
        initView();
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(360);
    }

    protected void initView() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(IMG_URL);
        if (mUrl.endsWith("gif")) {
            Glide.with(this).load(mUrl).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).fitCenter()
                    .into(new ImageViewTarget<GifDrawable>(mImg) {

                        @Override
                        protected void setResource(GifDrawable resource) {
                            view.setImageDrawable(resource);
                            if (!resource.isRunning()) {
                                resource.start();
                            }
                        }

                        @Override
                        public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
                            super.onResourceReady(resource, glideAnimation);
                            mProgress.setVisibility(View.GONE);
                        }
                    });
        } else {
            Glide.with(this).load(mUrl).fitCenter()
                    .into(new GlideDrawableImageViewTarget(mImg) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                            mProgress.setVisibility(View.GONE);
                        }
                    });
        }
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFabSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FileUtil.savePicture((Activity) mContext,mUrl);
            }
        });
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
