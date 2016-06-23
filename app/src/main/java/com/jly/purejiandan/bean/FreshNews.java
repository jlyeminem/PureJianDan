package com.jly.purejiandan.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 *
 * Created by jly on 2016/6/5.
 */
public class FreshNews implements Parcelable{

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    /**
     * id : 79701
     * url : http://jandan.net/2016/06/05/curb-online-shopping.html
     * title : 再买剁手！如何抑制网购瘾？
     * date : 2016-06-05 12:02:05
     * tags : [{"id":847,"slug":"%e5%bf%83%e7%90%86%e5%ad%a6","title":"心理学","description":"","post_count":380}]
     * author : {"id":584,"slug":"alexwhatever","name":"沸石","first_name":"","last_name":"","nickname":"沸石","url":"","description":""}
     * comment_count : 10
     * custom_fields : {"thumb_c":["http://tankr.net/s/custom/IPJN.jpg"]}
     */

    private boolean isRead;
    private int id;
    private String url;
    private String title;
    private String date;
    /**
     * id : 584
     * slug : alexwhatever
     * name : 沸石
     * first_name :
     * last_name :
     * nickname : 沸石
     * url :
     * description :
     */

    private Author author;
    private int comment_count;
    private CustomFields custom_fields;
    /**
     * id : 847
     * slug : %e5%bf%83%e7%90%86%e5%ad%a6
     * title : 心理学
     * description :
     * post_count : 380
     */

    private List<Tags> tags;

    public FreshNews(){

    }

    protected FreshNews(Parcel in) {
        id = in.readInt();
        url = in.readString();
        title = in.readString();
        date = in.readString();
        comment_count = in.readInt();
        author = in.readParcelable(Author.class.getClassLoader());
        custom_fields = in.readParcelable(CustomFields.class.getClassLoader());
//        Tags[] t = (Tags[]) in.readParcelableArray(Tags.class.getClassLoader());
//        Collections.addAll(tags, t);
    }

    public static final Creator<FreshNews> CREATOR = new Creator<FreshNews>() {
        @Override
        public FreshNews createFromParcel(Parcel in) {
            return new FreshNews(in);
        }

        @Override
        public FreshNews[] newArray(int size) {
            return new FreshNews[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public CustomFields getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomFields custom_fields) {
        this.custom_fields = custom_fields;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(url);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeInt(comment_count);
        dest.writeParcelable(author,flags);
        dest.writeParcelable(custom_fields,flags);
//        dest.writeParcelableArray((Parcelable[]) tags.toArray(),flags);
    }
}
