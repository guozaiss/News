package com.guozaiss.news.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Lenovo on 2016/5/10.
 */
public class ShareUtils {

    /**
     * 分享功能
     *
     * @param context       上下文
     * @param activityTitle Activity的名字
     * @param msgTitle      消息标题
     * @param msgText       消息内容
     * @param imgPath       图片路径，不分享图片则传null
     */
    public static void shareMsg(Context context, String activityTitle, String msgTitle, String msgText, String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

//    public static void shareText(Activity activity, String shareText) {
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
//        shareIntent.setType("text/plain");
//
//        //设置分享列表的标题，并且每次都显示分享列表
//        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
//    }
//
//    public static void shareText(Activity activity) {
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my Share text.");
//        shareIntent.setType("text/plain");
//
//        //设置分享列表的标题，并且每次都显示分享列表
//        activity.startActivity(Intent.createChooser(shareIntent, "娱乐新闻"));
//    }
//
//    public static void shareSingleImage(Activity activity, View view) {
//        String imagePath = Environment.getExternalStorageDirectory() + File.separator + "test.jpg";
//        //由文件得到uri
//        Uri imageUri = Uri.fromFile(new File(imagePath));
//        Log.d("share", "uri:" + imageUri);  //输出：file:///storage/emulated/0/test.jpg
//
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND);
//        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//        shareIntent.setType("image/*");
//        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
//    }
//
//    public static void shareMultipleImage(Activity activity, View view) {
//        ArrayList uriList = new ArrayList<>();
//
//        String path = Environment.getExternalStorageDirectory() + File.separator;
//        uriList.add(Uri.fromFile(new File(path + "australia_1.jpg")));
//        uriList.add(Uri.fromFile(new File(path + "australia_2.jpg")));
//        uriList.add(Uri.fromFile(new File(path + "australia_3.jpg")));
//
//        Intent shareIntent = new Intent();
//        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
//        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
//        shareIntent.setType("image/*");
//        activity.startActivity(Intent.createChooser(shareIntent, "分享到"));
//    }
}
