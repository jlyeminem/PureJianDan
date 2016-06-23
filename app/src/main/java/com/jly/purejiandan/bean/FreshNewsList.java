package com.jly.purejiandan.bean;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jly on 2016/6/2.
 */
public class FreshNewsList implements Parcelable {


    private String status;
    private int count;
    private int count_total;
    private int pages;
    private List<FreshNews> posts;

    protected FreshNewsList(Parcel in) {
        status = in.readString();
        count = in.readInt();
        count_total = in.readInt();
        pages = in.readInt();
    }

    public static final Creator<FreshNewsList> CREATOR = new Creator<FreshNewsList>() {
        @Override
        public FreshNewsList createFromParcel(Parcel in) {
            return new FreshNewsList(in);
        }

        @Override
        public FreshNewsList[] newArray(int size) {
            return new FreshNewsList[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<FreshNews> getPosts() {
        return posts;
    }

    public void setPosts(List<FreshNews> posts) {
        this.posts = posts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(count);
        dest.writeInt(count_total);
        dest.writeInt(pages);
    }

//    public static class FreshNews {
//        private int id;
//        private String url;
//        private String title;
//        private String date;
//        /**
//         * id : 592
//         * slug : zzjeff
//         * name : zzjeff
//         * first_name :
//         * last_name :
//         * nickname : zzjeff
//         * url :
//         * description :
//         */
//
//        private AuthorBean author;
//        private int comment_count;
//        private CustomFieldsBean custom_fields;
//        /**
//         * id : 847
//         * slug : %e5%bf%83%e7%90%86%e5%ad%a6
//         * title : 心理学
//         * description :
//         * post_count : 381
//         */
//
//        private List<TagsBean> tags;
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getUrl() {
//            return url;
//        }
//
//        public void setUrl(String url) {
//            this.url = url;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public AuthorBean getAuthor() {
//            return author;
//        }
//
//        public void setAuthor(AuthorBean author) {
//            this.author = author;
//        }
//
//        public int getComment_count() {
//            return comment_count;
//        }
//
//        public void setComment_count(int comment_count) {
//            this.comment_count = comment_count;
//        }
//
//        public CustomFieldsBean getCustom_fields() {
//            return custom_fields;
//        }
//
//        public void setCustom_fields(CustomFieldsBean custom_fields) {
//            this.custom_fields = custom_fields;
//        }
//
//        public List<TagsBean> getTags() {
//            return tags;
//        }
//
//        public void setTags(List<TagsBean> tags) {
//            this.tags = tags;
//        }
//
//        public static class AuthorBean {
//            private int id;
//            private String slug;
//            private String name;
//            private String first_name;
//            private String last_name;
//            private String nickname;
//            private String url;
//            private String description;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getSlug() {
//                return slug;
//            }
//
//            public void setSlug(String slug) {
//                this.slug = slug;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getFirst_name() {
//                return first_name;
//            }
//
//            public void setFirst_name(String first_name) {
//                this.first_name = first_name;
//            }
//
//            public String getLast_name() {
//                return last_name;
//            }
//
//            public void setLast_name(String last_name) {
//                this.last_name = last_name;
//            }
//
//            public String getNickname() {
//                return nickname;
//            }
//
//            public void setNickname(String nickname) {
//                this.nickname = nickname;
//            }
//
//            public String getUrl() {
//                return url;
//            }
//
//            public void setUrl(String url) {
//                this.url = url;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//        }
//
//        public static class CustomFieldsBean {
//            private List<String> thumb_c;
//
//            public List<String> getThumb_c() {
//                return thumb_c;
//            }
//
//            public void setThumb_c(List<String> thumb_c) {
//                this.thumb_c = thumb_c;
//            }
//        }
//
//        public static class TagsBean {
//            private int id;
//            private String slug;
//            private String title;
//            private String description;
//            private int post_count;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public String getSlug() {
//                return slug;
//            }
//
//            public void setSlug(String slug) {
//                this.slug = slug;
//            }
//
//            public String getTitle() {
//                return title;
//            }
//
//            public void setTitle(String title) {
//                this.title = title;
//            }
//
//            public String getDescription() {
//                return description;
//            }
//
//            public void setDescription(String description) {
//                this.description = description;
//            }
//
//            public int getPost_count() {
//                return post_count;
//            }
//
//            public void setPost_count(int post_count) {
//                this.post_count = post_count;
//            }
//        }
//    }
}
