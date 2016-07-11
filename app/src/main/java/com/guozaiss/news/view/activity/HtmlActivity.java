package com.guozaiss.news.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.guozaiss.news.R;
import com.guozaiss.news.common.base.BaseActivity;
import com.guozaiss.news.common.utils.ShareUtils;
import com.keymob.networks.AdManager;

public class HtmlActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private ProgressBar progress;
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webView);

        AdManager.getInstance().showInterstitial(this);

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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) {
                    /** 拨打电话 */
                    if (url.startsWith("tel:")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } else {
                        // 如果想继续加载目标页面则调用下面的语句
                        view.loadUrl(url);
                        // view.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
                        // 如果不想那url就是目标网址，如果想获取目标网页的内容那你可以用HTTP的API把网页扒下来。
                    }
                }
                return true;
            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (newProgress == 100) {// 网页加载完成
                    progress.setProgress(newProgress);
                    swipeRefreshLayout.setRefreshing(false);
                } else {// 加载中
                    progress.setProgress(newProgress);
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdUtils.init(mAdView, getApplicationContext());
    }
    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.html, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                ShareUtils.shareMsg(this, "我是标题", "标题", url, "/sdcard/Pictures/Screenshots/Screenshot_2016-05-17-17-09-47.png");
                return true;
            case R.id.action_openbrow:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        webView.reload();
    }

    @Override
    public void finish() {
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        super.finish();
    }
}