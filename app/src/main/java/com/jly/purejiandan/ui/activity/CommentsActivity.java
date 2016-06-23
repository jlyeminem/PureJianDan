package com.jly.purejiandan.ui.activity;

import android.os.Bundle;

import com.jly.purejiandan.base.BaseActivity;

/**
 * Created by jly on 2016/6/16.
 */
public class CommentsActivity extends BaseActivity {
    public static String ID_FRESH = "id_fresh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {
        int id = getIntent().getIntExtra(ID_FRESH,0);
        getComments(id);
    }

    private void getComments(int id) {

    }
}
