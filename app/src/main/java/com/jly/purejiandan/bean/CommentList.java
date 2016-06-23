package com.jly.purejiandan.bean;

import java.util.List;

/**
 * Created by jly on 2016/6/12.
 */
public class CommentList {
    private List<Comment> mComments;
    private List<CommentAuthor> mCommentAuthors;

    private CommentOptions options;

    private ThreadBean thread;
    private CursorBean cursor;
    private int code;
    private List<String> response;
    private List<String> hotPosts;

    public CommentOptions getOptions() {
        return options;
    }

    public void setOptions(CommentOptions options) {
        this.options = options;
    }

    public ThreadBean getThread() {
        return thread;
    }

    public void setThread(ThreadBean thread) {
        this.thread = thread;
    }

    public CursorBean getCursor() {
        return cursor;
    }

    public void setCursor(CursorBean cursor) {
        this.cursor = cursor;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public List<String> getHotPosts() {
        return hotPosts;
    }

    public void setHotPosts(List<String> hotPosts) {
        this.hotPosts = hotPosts;
    }

    public List<CommentAuthor> getCommentAuthors() {
        return mCommentAuthors;
    }

    public void setCommentAuthors(List<CommentAuthor> commentAuthors) {
        mCommentAuthors = commentAuthors;
    }

    public List<Comment> getComments() {
        return mComments;
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    public static class ThreadBean {
        private String thread_id;
        private int site_id;
        private String title;
        private String created_at;
        private String thread_key;
        private String url;
        private String agent;
        private String source;
        private String author_id;
        private int weibo_reposts;
        private int comments;
        private int dislikes;
        private int likes;
        private int reposts;
        private int views;
        private int post_enable;
        private List<?> meta;

        public String getThread_id() {
            return thread_id;
        }

        public void setThread_id(String thread_id) {
            this.thread_id = thread_id;
        }

        public int getSite_id() {
            return site_id;
        }

        public void setSite_id(int site_id) {
            this.site_id = site_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getThread_key() {
            return thread_key;
        }

        public void setThread_key(String thread_key) {
            this.thread_key = thread_key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAgent() {
            return agent;
        }

        public void setAgent(String agent) {
            this.agent = agent;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public int getWeibo_reposts() {
            return weibo_reposts;
        }

        public void setWeibo_reposts(int weibo_reposts) {
            this.weibo_reposts = weibo_reposts;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public int getDislikes() {
            return dislikes;
        }

        public void setDislikes(int dislikes) {
            this.dislikes = dislikes;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getReposts() {
            return reposts;
        }

        public void setReposts(int reposts) {
            this.reposts = reposts;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getPost_enable() {
            return post_enable;
        }

        public void setPost_enable(int post_enable) {
            this.post_enable = post_enable;
        }

        public List<?> getMeta() {
            return meta;
        }

        public void setMeta(List<?> meta) {
            this.meta = meta;
        }
    }

    public static class CursorBean {
        private int total;
        private int pages;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }
}
