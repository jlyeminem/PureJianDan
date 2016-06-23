package com.jly.purejiandan.db;

import android.content.Context;

import com.jly.purejiandan.App;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by jly on 2016/6/15.
 */
public class ReadFreshNews {
    private static ReadFreshNews instance;
    private static ReadFreshDao sReadFreshDao;
    private static DaoSession sDaoSession;
    private static DaoMaster sDaoMaster;
    private ReadFreshNews(){

    }
    public static ReadFreshNews getInstance(Context context){
        if (instance == null) {

            synchronized (ReadFreshNews.class) {
                if (instance == null) {
                    instance = new ReadFreshNews();
                }
            }

            sDaoSession = App.getDaoSession(context);
            sReadFreshDao = sDaoSession.getReadFreshDao();
        }
        return instance;
    }
    public List<Integer> getAllReadId(){
        List<Integer> list = new ArrayList<>();
        QueryBuilder<ReadFresh> query = sReadFreshDao.queryBuilder();
        if(query.list().size() > 0){
            for(ReadFresh readFresh:query.list() ){
                list.add(readFresh.getFreshId());
            }
        }
        return list;
    }
    public void addReadId(int id){
        ReadFresh readFresh = new ReadFresh(id);
        sReadFreshDao.insert(readFresh);
    }
}
