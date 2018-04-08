package com.example.test.hotfix;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gx on 2017/6/21.
 */
public class FixUtils {

    public static void downFix(final Activity context) {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(1000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(1000, TimeUnit.SECONDS)//设置连接超时时间
                .build();
        RequestBody formBody = new FormBody.Builder()
                .build();
        final Request request = new Request.Builder()
                .url("http://orme0toyz.bkt.clouddn.com/old_fix.apatch")
                .post(formBody)
                .build();
//        AppContext.patchManager.removeAllPatch();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "下载失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) {
                InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream;
                try {
                    String path = context.getCacheDir() + File.separator + "fix.apatch";
                    fileOutputStream = new FileOutputStream(path);
                    byte[] buffer = new byte[2048];
                    int len = 0;
                    while ((len = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.flush();
//                    AppContext.patchManager.addPatch(path);
                    Toast.makeText(context, "修复完成", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e("Guo", "IOException", e);
                }
            }
        });
    }
}