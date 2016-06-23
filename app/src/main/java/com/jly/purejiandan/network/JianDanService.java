package com.jly.purejiandan.network;

import com.jly.purejiandan.bean.CommentListForFresh;
import com.jly.purejiandan.bean.FreshNewsDetail;
import com.jly.purejiandan.bean.FreshNewsList;
import com.jly.purejiandan.bean.JokeList;
import com.jly.purejiandan.bean.OXPictureList;
import com.jly.purejiandan.bean.PictureList;
import com.jly.purejiandan.bean.VideoList;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 * Created by jly on 2016/6/5.
 */
public interface JianDanService {

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=1")
    Observable<FreshNewsList> getLatestNews();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&page=")
    Observable<FreshNewsList> getBeforeNews(@Query("page") int page);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=get_post&include=content&id=")
    Observable<FreshNewsDetail> getFreshNewsDetail(@Query("id") int id);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=1")
    Observable<JokeList> getLatestJokes();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=")
    Observable<JokeList> getBeforeJokes(@Query("page") int page);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=1")
    Observable<OXPictureList> getLatestOXPictures();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&page=")
    Observable<OXPictureList> getBeforeOXPictures(@Query("page") int page);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=1")
    Observable<PictureList> getLatestPictures();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=jandan.get_pic_comments&page=")
    Observable<PictureList> getBeforePictures(@Query("page") int page);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("?oxwlxojflwblxbsapi=jandan.get_video_comments&page=1")
    Observable<VideoList> getLatestVideos();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=jandan.get_video_comments&page=")
    Observable<VideoList> getBeforeVideos(@Query("page") int page);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("?oxwlxojflwblxbsapi=get_post&include=comments&id=")
    Observable<CommentListForFresh> getCommentListForFresh(@Query("id") int id);

}
