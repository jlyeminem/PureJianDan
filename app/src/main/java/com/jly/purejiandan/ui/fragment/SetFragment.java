package com.jly.purejiandan.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jly.purejiandan.R;
import com.jly.purejiandan.utils.Constant;
import com.jly.purejiandan.utils.PrefUtil;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SetFragment extends Fragment {
    @Bind(R.id.tv_night)
    TextView mTvNight;


    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.bind(this, view);
        mTvNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNight = PrefUtil.isNight();
                if (isNight) {
                    PrefUtil.setDay();
                    getActivity().setTheme(Constant.RESOURCES_DAYTHEME);
                } else {
                    PrefUtil.setNight();
                    getActivity().setTheme(Constant.RESOURCES_NIGHTTHEME);
                }
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
