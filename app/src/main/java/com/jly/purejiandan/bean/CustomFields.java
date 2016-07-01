package com.jly.purejiandan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jly on 2016/6/5.
 */
public  class CustomFields implements Parcelable {
    private List<String> thumb_c;

    public CustomFields(){

    }
    protected CustomFields(Parcel in) {
        thumb_c = in.createStringArrayList();
    }

    public static final Creator<CustomFields> CREATOR = new Creator<CustomFields>() {
        @Override
        public CustomFields createFromParcel(Parcel in) {
            return new CustomFields(in);
        }

        @Override
        public CustomFields[] newArray(int size) {
            return new CustomFields[size];
        }
    };

    public List<String> getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(List<String> thumb_c) {
        this.thumb_c = thumb_c;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(thumb_c);
    }
}