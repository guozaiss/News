package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class HtmlActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //后面 “android” 相当于一个标志符
//        webView.addJavascriptInterface(new JavaScriptInterface(), "android");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                //返回值为true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);

                String js = "";
                js += " function myFunction(){";
//                js += "document.getElementById('ass').innerHTML=\"New text!\";";
                js += "document.body.style.backgroundColor=\"#000000\";";
                js += "}";
                webView.loadUrl("javascript:" + js);
                webView.loadUrl("javascript:myFunction()");
            }
        });
        webView.loadUrl("file:///android_asset/aa.html");
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); // goBack()表示返回WebView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public class JavaScriptInterface {

        @JavascriptInterface
        public void getTagVal(final String val) {
            try {
                Toast.makeText(HtmlActivity.this, val, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("AAAAA", e.getMessage() + "");
            }
        }
    }
}
