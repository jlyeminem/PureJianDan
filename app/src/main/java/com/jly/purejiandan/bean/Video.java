package com.jly.purejiandan.bean;

import java.util.List;

/**
 * id : XMTYwMTg0MDQxMg==
 * title : Hugh Laurie Sings the Blues - St. James Infirmary' from the NYTimes
 * description :
 * thumbnail : http://r3.ykimg.com/054204085758C7686A0A4C047040E829
 * thumbnail_v2 : http://r4.ykimg.com/054204085758C7686A0A4C047040E829
 * is_panorama : 0
 * duration : 445.00
 * comment_count : 0
 * favorite_count : 0
 * up_count : 0
 * down_count : 0
 * published : 2016-06-09 09:35:44
 * copyright_type : original
 * public_type : all
 * state : normal
 * streamtypes : ["flvhd","3gphd"]
 * category : 音乐
 * view_count : 176
 * paid : 0
 * link : http://v.youku.com/v_show/id_XMTYwMTg0MDQxMg==.html
 * player : http://player.youku.com/player.php/sid/XMTYwMTg0MDQxMg==/partnerid/49cd576fdfc4be20/v.swf
 * operation_limit : []
 * user : {"id":"140624983","name":"Herbst1109","link":"http://i.youku.com/u/UNTYyNDk5OTMy"}
 * video_source : youku
 */
public class Video {
    private String id;
    private String title;
    private String description;
    private String thumbnail;
    private String thumbnail_v2;
    private String is_panorama;
    private String duration;
    private String comment_count;
    private String favorite_count;
    private String up_count;
    private String down_count;
    private String published;
    private String copyright_type;
    private String public_type;
    private String state;
    private String category;
    private int view_count;
    private int paid;
    private String link;
    private String player;
    /**
     * id : 140624983
     * name : Herbst1109
     * link : http://i.youku.com/u/UNTYyNDk5OTMy
     */

    private UserBean user;
    private String video_source;
    private List<String> streamtypes;
    private List<?> operation_limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail_v2() {
        return thumbnail_v2;
    }

    public void setThumbnail_v2(String thumbnail_v2) {
        this.thumbnail_v2 = thumbnail_v2;
    }

    public String getIs_panorama() {
        return is_panorama;
    }

    public void setIs_panorama(String is_panorama) {
        this.is_panorama = is_panorama;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(String favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getUp_count() {
        return up_count;
    }

    public void setUp_count(String up_count) {
        this.up_count = up_count;
    }

    public String getDown_count() {
        return down_count;
    }

    public void setDown_count(String down_count) {
        this.down_count = down_count;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getCopyright_type() {
        return copyright_type;
    }

    public void setCopyright_type(String copyright_type) {
        this.copyright_type = copyright_type;
    }

    public String getPublic_type() {
        return public_type;
    }

    public void setPublic_type(String public_type) {
        this.public_type = public_type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getVideo_source() {
        return video_source;
    }

    public void setVideo_source(String video_source) {
        this.video_source = video_source;
    }

    public List<String> getStreamtypes() {
        return streamtypes;
    }

    public void setStreamtypes(List<String> streamtypes) {
        this.streamtypes = streamtypes;
    }

    public List<?> getOperation_limit() {
        return operation_limit;
    }

    public void setOperation_limit(List<?> operation_limit) {
        this.operation_limit = operation_limit;
    }

    public static class UserBean {
        private String id;
        private String name;
        private String link;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
