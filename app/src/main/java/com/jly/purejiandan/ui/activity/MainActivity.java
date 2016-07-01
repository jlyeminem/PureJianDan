package com.jly.purejiandan.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jly.purejiandan.bean.NetWorkEvent;
import com.jly.purejiandan.R;
import com.jly.purejiandan.base.BaseActivity;
import com.jly.purejiandan.ui.fragment.FreshListFragment;
import com.jly.purejiandan.ui.fragment.MenuFragment;
import com.jly.purejiandan.utils.NetUtil;
import com.jly.purejiandan.utils.ShowToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private BroadcastReceiver mNetStateReceiver;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mNetStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
                    if(NetUtil.isNetworkConnected()){
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.AVAILABLE));
                    }else{
                        EventBus.getDefault().post(new NetWorkEvent(NetWorkEvent.UNAVAILABLE));
                    }
                }
            }
        };
        registerReceiver(mNetStateReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    public void onEventMainThread(NetWorkEvent event){
        if(NetWorkEvent.UNAVAILABLE==event.getType()){
            MaterialDialog materialDialog =new MaterialDialog.Builder(this)
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
            if(!materialDialog.isShowing()){
                materialDialog.show();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - exitTime > 1500)){
                ShowToast.Short("再按一次退出程序");
                exitTime = System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mNetStateReceiver);
    }

    protected void initView() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setBackgroundColor(Color.BLUE);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar , R.string.app_name, R.string.app_name) {
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
        setFragment(R.id.menu_container,new MenuFragment());
        setFragment(R.id.content_container,new FreshListFragment());
    }

    public void closeDrawers(){
        mDrawerLayout.closeDrawers();
    }
}
