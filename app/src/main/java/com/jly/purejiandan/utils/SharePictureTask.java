package com.jly.purejiandan.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.jly.purejiandan.App;

import java.io.File;

/**
 * Created by jly on 2016/6/12.
 */
public class SharePictureTask extends AsyncTask<String, Void, File>{
    private final Context context;

    public SharePictureTask(Context context) {
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
        FileProvider fileProvider = new FileProvider();
        Uri uri = Uri.fromFile(result);
        share(uri);
    }

    private void share(Uri result) {

        Intent waIntent = new Intent();
        waIntent.setAction(Intent.ACTION_SEND);
        waIntent.setType("image/*");
        waIntent.putExtra(Intent.EXTRA_TEXT, "sharing from my app");
        waIntent.putExtra(Intent.EXTRA_STREAM, result);
        context.startActivity(waIntent);
    }

}
