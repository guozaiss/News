package com.guozaiss.news.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.guozaiss.news.R;

public class HtmlActivity extends AppCompatActivity {

    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        String url = getIntent().getStringExtra("url");
        WebView webView = (WebView) findViewById(R.id.webView);
        //WebSettings
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(false);//支持javascript
        settings.setSupportZoom(true);// 设置可以支持缩放
        settings.setBuiltInZoomControls(true);// 设置出现缩放工具
        settings.setUseWideViewPort(true);//扩大比例的缩放
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//优先使用缓存
        webView.loadUrl(url);
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setMax(100);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {// 网页加载完成
                    progress.setProgress(newProgress);
                } else {// 加载中
                    progress.setProgress(newProgress);
                }
            }
        });
    }
}
