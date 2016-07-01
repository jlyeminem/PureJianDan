package com.jly.purejiandan.ui;

import android.app.Fragment;

/**
 *
 * Created by jly on 2016/6/2.
 */
public class FragmentMenuItem {
    public enum FragmentType {
        FreshNews, BoringPicture, Sister, Joke, Video
    }

    private String title;
    private int resourceId;
    private FragmentType type;
    private Class<? extends Fragment> fragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public Class<? extends Fragment> getFragment() {
        return fragment;
    }

    public void setFragment(Class<? extends Fragment> fragment) {
        this.fragment = fragment;
    }


    public FragmentType getType() {
        return type;
    }

    public void setType(FragmentType type) {
        this.type = type;
    }

    public FragmentMenuItem() {
    }

    public FragmentMenuItem(String title, int resourceId, Class<? extends Fragment> fragment) {
        this.resourceId = resourceId;
        this.title = title;
        this.fragment = fragment;
    }

    public FragmentMenuItem(String title, int resourceId, FragmentType type, Class<? extends Fragment> fragment) {
        this.title = title;
        this.resourceId = resourceId;
        this.type = type;
        this.fragment = fragment;
    }
}
