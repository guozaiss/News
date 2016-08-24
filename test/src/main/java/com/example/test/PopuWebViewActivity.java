package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.PopupWindow;

public class PopuWebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popu_web_view);
        WebView container = (WebView) findViewById(R.id.container);
        String url="https://github.com/";
        WebView inflate = (WebView) View.inflate(this, R.layout.popu_webview, null);
        final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.update();
        inflate.loadUrl(url);
//        container.loadUrl(url);
        container.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });
    }
}
