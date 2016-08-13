package com.jly.purejiandan.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jly.purejiandan.R;
import com.jly.purejiandan.base.BaseActivity;
import com.jly.purejiandan.bean.NetWorkEvent;
import com.jly.purejiandan.ui.fragment.FreshListFragment;
import com.jly.purejiandan.ui.fragment.JokeFragment;
import com.jly.purejiandan.ui.fragment.OXPictureFragment;
import com.jly.purejiandan.ui.fragment.PictureFragment;
import com.jly.purejiandan.ui.fragment.VideoFragment;
import com.jly.purejiandan.utils.NetUtil;
import com.jly.purejiandan.utils.RxBus;
import com.jly.purejiandan.utils.ShowToast;
import com.jly.purejiandan.utils.StatusBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.navigationView)
    NavigationView mNavigationView;

    private BroadcastReceiver mNetStateReceiver;
    private long exitTime;
    private Subscription rxSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.compat(this, ContextCompat.getColor(this,R.color.colorPrimary));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
        receiveEvent();
    }

    private void receiveEvent() {
        rxSubscription = RxBus.getDefault().toObservable(NetWorkEvent.class)
                .subscribe(new Action1<NetWorkEvent>() {
                    @Override
                    public void call(NetWorkEvent netWorkEvent) {
                        onEvent(netWorkEvent);
                    }
                });
    }

    private void initData() {
        mNetStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                    if (NetUtil.isNetworkConnected()) {
                        RxBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    } else {
                        RxBus.getDefault().post(new NetWorkEvent(NetWorkEvent.UNAVAILABLE));
                    }
                }
            }
        };
        registerReceiver(mNetStateReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void onEvent(NetWorkEvent event) {
        if (NetWorkEvent.UNAVAILABLE == event.getType()) {
            MaterialDialog materialDialog = new MaterialDialog.Builder(this)
                    .title("未发现网络连接")
                    .content("去开启网络？")
                    .positiveText("是")
                    .negativeText("否")
                    .backgroundColor(Color.BLUE)
                    .contentColor(Color.WHITE)
                    .titleColor(Color.WHITE)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(intent);
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                        }
                    })
                    .cancelable(false)
                    .build();
            if (!materialDialog.isShowing()) {
                materialDialog.show();
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime > 1500)) {
                ShowToast.Short("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
        unregisterReceiver(mNetStateReceiver);
        if (!rxSubscription.isUnsubscribed()) {
            rxSubscription.unsubscribe();
        }
    }

    protected void initView() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary));
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();// creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setCheckedItem(R.id.nav_freshNews);
        mNavigationView.setNavigationItemSelectedListener(this);
        setFragment(R.id.content_container, new FreshListFragment());
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_freshNews:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                setFragment(R.id.content_container, new FreshListFragment());
                break;
            case R.id.nav_boringPicture:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                setFragment(R.id.content_container, new PictureFragment());
                break;
            case R.id.nav_sister:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                setFragment(R.id.content_container, new OXPictureFragment());
                break;
            case R.id.nav_joke:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                setFragment(R.id.content_container, new JokeFragment());
                break;
            case R.id.nav_movie:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                setFragment(R.id.content_container, new VideoFragment());
                break;
            case R.id.nav_setting:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                ShowToast.Short("功能还未实现");
                break;
            default:
                break;
        }
        return true;
    }
}
