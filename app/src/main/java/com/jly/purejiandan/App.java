package com.jly.purejiandan;

import android.app.Application;
import android.content.Context;

import com.jly.purejiandan.db.DaoMaster;
import com.jly.purejiandan.db.DaoSession;
import com.jly.purejiandan.db.ReadFreshDao;
import com.jly.purejiandan.utils.AppContextUtil;


/**
 *
 * Created by jly on 16/3/17.
 */
public class App extends Application {

    private static Context mApplicationContext;
    private static DaoMaster sDaoMaster;
    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
        AppContextUtil.init(this);
    }

    // 获取ApplicationContext
    public static Context getContext() {
        return mApplicationContext;
    }

    public static DaoMaster getDaoMaster(Context context){
        if(sDaoMaster == null){
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, ReadFreshDao.TABLENAME,null);
            sDaoMaster =new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    public static DaoSession getDaoSession(Context context){
        if(sDaoSession == null){
            if(sDaoMaster == null){
                sDaoMaster = getDaoMaster(context);
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }
}
