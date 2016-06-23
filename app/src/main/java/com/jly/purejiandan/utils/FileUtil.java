package com.jly.purejiandan.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.jly.purejiandan.R;
import com.jly.purejiandan.base.BaseActivity;

public class FileUtil {


    /**
     * 保存图片
     *
     * @param activity
     * @param picUrl
     */
    public static void savePicture(Activity activity, String picUrl) {
        new SaveTask(activity).execute(picUrl);
    }

    /**
     * 分享图片
     * @param activity
     * @param picUrl
     */
    public static void sharePicture(Activity activity,String picUrl){
        new SharePictureTask(activity).execute(picUrl);
    }

    /**
     * 分享文字
     * @param activity
     * @param shareText
     */
    public static void shareText(Activity activity, String shareText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                shareText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(intent, activity.getResources().getString(R
                .string.app_name)));
    }

    /**
     * 复制文字
     * @param activity
     * @param copyText
     */
    public static void copyText(Activity activity, String copyText) {
        ClipboardManager clip = (ClipboardManager)
                activity.getSystemService(Context
                        .CLIPBOARD_SERVICE);
        clip.setPrimaryClip(ClipData.newPlainText
                (null, copyText));
        ShowToast.Short(BaseActivity.COPY_SUCCESS);
    }



}
