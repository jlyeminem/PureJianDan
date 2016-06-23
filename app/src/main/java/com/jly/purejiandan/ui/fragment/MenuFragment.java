package com.jly.purejiandan.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jly.purejiandan.R;
import com.jly.purejiandan.ui.activity.SetActivity;
import com.jly.purejiandan.ui.adapter.MenuAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jly on 2016/6/2.
 */
public class MenuFragment extends Fragment {
    @Bind(R.id.rv_menu)
    RecyclerView mRvMenu;
    @Bind(R.id.rl_set)
    RelativeLayout mRlSet;
    @Bind(R.id.rl_night)
    RelativeLayout mRlNight;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mRvMenu.setLayoutManager(layoutManager);
        mRlSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
//                ((MainActivity)getActivity()).closeDrawers();
            }
        });
//        mRlNight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isNight = PrefUtil.isNight();
//                if (isNight) {
//                    PrefUtil.setDay();
//                    getActivity().setTheme(Constant.RESOURCES_DAYTHEME);
//                } else {
//                    PrefUtil.setNight();
//                    getActivity().setTheme(Constant.RESOURCES_NIGHTTHEME);
//                }
//                setDrawableCahe();
//            }
//        });
    }
//    private void setDrawableCahe() {
//        //设置false清除缓存
//        mViewGroup.setDrawingCacheEnabled(false);
//        //设置true之后可以获取Bitmap
//        mViewGroup.setDrawingCacheEnabled(true);
//        mIvDrawer.setImageBitmap(mViewGroup.getDrawingCache());
//        mIvDrawer.setAlpha(1f);
//        mIvDrawer.setVisibility(View.VISIBLE);
//    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MenuAdapter adapter = new MenuAdapter(this.getActivity());
        mRvMenu.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
