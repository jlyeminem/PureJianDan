package com.jly.purejiandan.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jly on 2016/6/5.
 */
public  class Author implements Parcelable {
    private int id;
    private String slug;
    private String name;
    private String first_name;
    private String last_name;
    private String nickname;
    private String url;
    private String description;

    public Author(){

    }
    protected Author(Parcel in) {
        id = in.readInt();
        slug = in.readString();
        name = in.readString();
        first_name = in.readString();
        last_name = in.readString();
        nickname = in.readString();
        url = in.readString();
        description = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(slug);
        dest.writeString(name);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(nickname);
        dest.writeString(url);
        dest.writeString(description);
    }
}
