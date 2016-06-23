package com.jly.purejiandan.network;

import com.jly.purejiandan.App;
import com.jly.purejiandan.bean.CommentListForFresh;
import com.jly.purejiandan.bean.FreshNewsDetail;
import com.jly.purejiandan.bean.FreshNewsList;
import com.jly.purejiandan.bean.JokeList;
import com.jly.purejiandan.bean.OXPictureList;
import com.jly.purejiandan.bean.PictureList;
import com.jly.purejiandan.bean.VideoList;
import com.jly.purejiandan.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 *
 * Created by jly on 2016/6/5.
 */
public class RetrofitManager {
    public static String BASE_JIANDAN_URL="http://jandan.net/";
    //短缓存有效期为1分钟
    public static final int CACHE_STALE_SHORT = 60;
    //长缓存有效期为7天
    public static final int CACHE_STALE_LONG = 60 * 60 * 24 * 7;

    public static final String CACHE_CONTROL_AGE = "Cache-Control: public, max-age=";
    
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private JianDanService mJianDanService;

    public static RetrofitManager builder(){
        return new RetrofitManager();
    }
    private RetrofitManager(){
        initOkHttpClient();
        mRetrofit =new Retrofit.Builder()
                .baseUrl(BASE_JIANDAN_URL)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mJianDanService = mRetrofit.create(JianDanService.class);
    }

    private void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {

                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"),
                            1024 * 1024 * 500);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }
    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_LONG)
                        .removeHeader("Pragma").build();
            }
        }
    };
    public Observable<FreshNewsList> getLatestNews(){
        return mJianDanService.getLatestNews();
    }
    public Observable<FreshNewsList> getBeforeNews(int page){
        return mJianDanService.getBeforeNews(page);
    }
    public Observable<FreshNewsDetail> getFreshNewsDetail(int id){
        return mJianDanService.getFreshNewsDetail(id);
    }
    public Observable<JokeList> getLastestJokes(){
        return mJianDanService.getLatestJokes();
    }
    public Observable<JokeList> getBeforeJokes(int page){
        return mJianDanService.getBeforeJokes(page);
    }
    public Observable<OXPictureList> getLatestOXPictures(){
        return mJianDanService.getLatestOXPictures();
    }
    public Observable<OXPictureList> getBeforeOXPictures(int page){
        return mJianDanService.getBeforeOXPictures(page);
    }
    public Observable<PictureList> getLatestPictures(){
        return mJianDanService.getLatestPictures();
    }
    public Observable<PictureList> getBeforePictures(int page){
        return mJianDanService.getBeforePictures(page);
    }
    public Observable<VideoList> getLatestVideos(){
        return mJianDanService.getLatestVideos();
    }
    public Observable<VideoList> getBeforeVideos(int page){
        return mJianDanService.getBeforeVideos(page);
    }
    public Observable<CommentListForFresh> getCommentListForFresh(int id){
        return mJianDanService.getCommentListForFresh(id);
    }
}
