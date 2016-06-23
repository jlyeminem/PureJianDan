package com.jly.purejiandan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jly on 2016/6/5.
 */
public class Tags implements Parcelable {
    private int id;
    private String slug;
    private String title;
    private String description;
    private int post_count;

    protected Tags(Parcel in) {
        id = in.readInt();
        slug = in.readString();
        title = in.readString();
        description = in.readString();
        post_count = in.readInt();
    }

    public static final Creator<Tags> CREATOR = new Creator<Tags>() {
        @Override
        public Tags createFromParcel(Parcel in) {
            return new Tags(in);
        }

        @Override
        public Tags[] newArray(int size) {
            return new Tags[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPost_count() {
        return post_count;
    }

    public void setPost_count(int post_count) {
        this.post_count = post_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(slug);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(post_count);
    }
}
