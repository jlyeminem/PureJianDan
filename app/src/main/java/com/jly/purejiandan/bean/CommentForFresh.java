package com.jly.purejiandan.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jly on 2016/6/16.
 */
public class CommentForFresh implements Comparable {

    /**
     * id : 3161106
     * name : &amp;
     * url :
     * date : 2016-06-05 12:36:11
     * content : <p>说实话，多挣点钱就行了</p>

     * parent : 0
     * vote_positive : 26
     * vote_negative : 4
     * index : 7
     */

    private int id;
    private String name;
    private String url;
    private String date;
    private String content;
    private int parent;
    private int vote_positive;
    private int vote_negative;
    private int index;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getVote_positive() {
        return vote_positive;
    }

    public void setVote_positive(int vote_positive) {
        this.vote_positive = vote_positive;
    }

    public int getVote_negative() {
        return vote_negative;
    }

    public void setVote_negative(int vote_negative) {
        this.vote_negative = vote_negative;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(Object another) {
        String anotherTimeString = ((CommentForFresh) another).getDate();
        String thisTimeString = getDate();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));

        try {
            Date anotherDate = simpleDateFormat.parse(anotherTimeString);
            Date thisDate = simpleDateFormat.parse(thisTimeString);
            return -thisDate.compareTo(anotherDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
