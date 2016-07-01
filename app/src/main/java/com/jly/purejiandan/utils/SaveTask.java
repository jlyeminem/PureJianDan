package com.jly.purejiandan.utils;

import android.content.Context;
import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.jly.purejiandan.App;
import com.jly.purejiandan.base.BaseActivity;

import java.io.File;

/**
 * Created by jly on 2016/6/12.
 */
public class SaveTask extends AsyncTask<String ,Void ,File> {

    private final Context context;
    public SaveTask(Context context) {
        this.context = context;
    }
    @Override
    protected File doInBackground(String... params) {

        FutureTarget<File> future = Glide.with(App.getContext())
                .load(params[0])
                .downloadOnly(500, 500);

        File file = null;
        try {
            file = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    protected void onPostExecute(File result) {
        if (result == null) {
            return ;
        }
        show(result);
    }

    private void show(File file) {
        ShowToast.Short(BaseActivity.SAVE_SUCCESS+"  已保存在"+file.toString());
    }

}
