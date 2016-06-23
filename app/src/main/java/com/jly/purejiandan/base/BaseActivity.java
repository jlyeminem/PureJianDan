package com.jly.purejiandan.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jly.purejiandan.utils.PrefUtil;

/**
 *
 * Created by jly on 2016/4/6.
 */
public class BaseActivity extends AppCompatActivity implements ConstString{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(PrefUtil.getThemeRes());
    }

    public  void setFragment(int id , Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(id,fragment);
        ft.commit();
    }
    protected void initView(){

    }
}
