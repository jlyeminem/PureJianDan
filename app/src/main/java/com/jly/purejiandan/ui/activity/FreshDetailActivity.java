package com.jly.purejiandan.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.jly.purejiandan.R;
import com.jly.purejiandan.bean.FreshNews;
import com.jly.purejiandan.ui.fragment.FreshDetailFragment;
import com.jly.purejiandan.utils.swipeback.SwipeBackActivity;
import com.jly.purejiandan.utils.swipeback.SwipeBackLayout;

public class FreshDetailActivity extends SwipeBackActivity {
    public static final String KEY_FRESH = "key_fresh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_detail);
        initView();
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(360);
    }

    public static void start(Context context, FreshNews news) {
        Intent intent = new Intent(context, FreshDetailActivity.class);
        intent.putExtra(KEY_FRESH, news);
        context.startActivity(intent);
    }
    
    protected void initView() {
        Intent intent = getIntent();
        FreshNews freshNews = intent.getParcelableExtra(KEY_FRESH);
        FreshDetailFragment fragment =FreshDetailFragment.newInstance(freshNews);
        setFragment(R.id.fl_common_container, fragment);
    }

    public  void setFragment(int id , Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id,fragment);
        ft.commit();
    }

}
