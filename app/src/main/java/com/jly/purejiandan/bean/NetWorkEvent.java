package com.jly.purejiandan.bean;

/**
 * Created by jly on 2016/6/14.
 */
public class NetWorkEvent {
    public static final int AVAILABLE = 1;
    public static final int UNAVAILABLE = 0;

    private int mType;

    public NetWorkEvent(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
