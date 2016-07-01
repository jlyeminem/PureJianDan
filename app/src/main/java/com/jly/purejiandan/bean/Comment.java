package com.jly.purejiandan.bean;

import java.util.List;

/**
 * Created by jly on 2016/6/16.
 */
public class Comment {

    /**
     * post_id : 6293687555594388226
     * thread_id : 6293686517680308993
     * status : approved
     * source : duoshuo
     * type : duoshuo
     * message : fire in the hole
     * created_at : 2016-06-08T13:22:04+08:00
     * privileges : []
     * parent_id : 0
     * root_id : 0
     * reposts : 0
     * comments : 0
     * author_id : 430989
     * author_key : 0
     * agent : Mozilla/5.0 (Linux; U; Android 4.4.4; zh-cn; HM 2A Build/KTU84Q) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 MQQBrowser/6.1 Mobile Safari/537.36
     * likes : 1
     * dislikes : 0
     * reports : 0
     * parents : []
     * author : {"user_id":"430989","name":"EAGL3","avatar_url":"http://app.qlogo.cn/mbloghead/efbd12ea493488876b6a/50","url":"http://www.mps.gov.cn/","threads":"","comments":"487","weibo_uid":"","qq_uid":"F021135A87FBFC5A72A906B7336ACBE0","renren_uid":"","kaixin_uid":"","douban_uid":"","netease_uid":"","sohu_uid":"","baidu_uid":"","msn_uid":"","google_uid":"","taobao_uid":""}
     * ip : 112.17.246.181
     * iplocation : 中国
     * is_top : 0
     */

    private String post_id;
    private String thread_id;
    private String status;
    private String source;
    private String type;
    private String message;
    private String created_at;
    private int parent_id;
    private int root_id;
    private int reposts;
    private int comments;
    private String author_id;
    private int author_key;
    private String agent;
    private int likes;
    private int dislikes;
    private int reports;
    /**
     * user_id : 430989
     * name : EAGL3
     * avatar_url : http://app.qlogo.cn/mbloghead/efbd12ea493488876b6a/50
     * url : http://www.mps.gov.cn/
     * threads :
     * comments : 487
     * weibo_uid :
     * qq_uid : F021135A87FBFC5A72A906B7336ACBE0
     * renren_uid :
     * kaixin_uid :
     * douban_uid :
     * netease_uid :
     * sohu_uid :
     * baidu_uid :
     * msn_uid :
     * google_uid :
     * taobao_uid :
     */

    private AuthorBean author;
    private String ip;
    private String iplocation;
    private int is_top;
    private List<?> privileges;
    private List<?> parents;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getRoot_id() {
        return root_id;
    }

    public void setRoot_id(int root_id) {
        this.root_id = root_id;
    }

    public int getReposts() {
        return reposts;
    }

    public void setReposts(int reposts) {
        this.reposts = reposts;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(int author_key) {
        this.author_key = author_key;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIplocation() {
        return iplocation;
    }

    public void setIplocation(String iplocation) {
        this.iplocation = iplocation;
    }

    public int getIs_top() {
        return is_top;
    }

    public void setIs_top(int is_top) {
        this.is_top = is_top;
    }

    public List<?> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<?> privileges) {
        this.privileges = privileges;
    }

    public List<?> getParents() {
        return parents;
    }

    public void setParents(List<?> parents) {
        this.parents = parents;
    }

    public static class AuthorBean {
        private String user_id;
        private String name;
        private String avatar_url;
        private String url;
        private String threads;
        private String comments;
        private String weibo_uid;
        private String qq_uid;
        private String renren_uid;
        private String kaixin_uid;
        private String douban_uid;
        private String netease_uid;
        private String sohu_uid;
        private String baidu_uid;
        private String msn_uid;
        private String google_uid;
        private String taobao_uid;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThreads() {
            return threads;
        }

        public void setThreads(String threads) {
            this.threads = threads;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getWeibo_uid() {
            return weibo_uid;
        }

        public void setWeibo_uid(String weibo_uid) {
            this.weibo_uid = weibo_uid;
        }

        public String getQq_uid() {
            return qq_uid;
        }

        public void setQq_uid(String qq_uid) {
            this.qq_uid = qq_uid;
        }

        public String getRenren_uid() {
            return renren_uid;
        }

        public void setRenren_uid(String renren_uid) {
            this.renren_uid = renren_uid;
        }

        public String getKaixin_uid() {
            return kaixin_uid;
        }

        public void setKaixin_uid(String kaixin_uid) {
            this.kaixin_uid = kaixin_uid;
        }

        public String getDouban_uid() {
            return douban_uid;
        }

        public void setDouban_uid(String douban_uid) {
            this.douban_uid = douban_uid;
        }

        public String getNetease_uid() {
            return netease_uid;
        }

        public void setNetease_uid(String netease_uid) {
            this.netease_uid = netease_uid;
        }

        public String getSohu_uid() {
            return sohu_uid;
        }

        public void setSohu_uid(String sohu_uid) {
            this.sohu_uid = sohu_uid;
        }

        public String getBaidu_uid() {
            return baidu_uid;
        }

        public void setBaidu_uid(String baidu_uid) {
            this.baidu_uid = baidu_uid;
        }

        public String getMsn_uid() {
            return msn_uid;
        }

        public void setMsn_uid(String msn_uid) {
            this.msn_uid = msn_uid;
        }

        public String getGoogle_uid() {
            return google_uid;
        }

        public void setGoogle_uid(String google_uid) {
            this.google_uid = google_uid;
        }

        public String getTaobao_uid() {
            return taobao_uid;
        }

        public void setTaobao_uid(String taobao_uid) {
            this.taobao_uid = taobao_uid;
        }
    }
}
