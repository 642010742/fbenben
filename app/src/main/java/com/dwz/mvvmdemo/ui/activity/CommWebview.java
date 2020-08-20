package com.dwz.mvvmdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dwz.mvvmdemo.R;

import library.App.AppConstants;

import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

public class CommWebview extends AppCompatActivity {

    private WebView comWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comm_webview);

        comWeb = findViewById(R.id.comWeb);
        TextView comWebTitle = findViewById(R.id.title);

        String title = getIntent().getStringExtra(AppConstants.IntentKey.WEB_TITLE);
        String url = getIntent().getStringExtra(AppConstants.IntentKey.WEB_URL);

        comWebTitle.setText(title);

        if ((url.startsWith("http"))) {
            comWeb.loadUrl(url);
        } else {
            comWeb.loadDataWithBaseURL(null, getHtmlData(url), "text/html", "utf-8", null);
        }

        initWebSetting();
        setWebviewClient();
        initListener();
    }

    //初始化websetting
    private void initWebSetting() {
        WebSettings webSettings = comWeb.getSettings();
        //设置背景颜色
//        vm.bind.comWeb.setBackgroundColor(0);
        //是否允许执行js
        webSettings.setJavaScriptEnabled(true);
        //DOM Storage
        webSettings.setDomStorageEnabled(true);
        // 实现8倍缓存
//        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        //是否使用缓存
        webSettings.setAppCacheEnabled(false);
//        webSettings.setCacheMode(LOAD_NO_CACHE);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setDatabaseEnabled(true);
        //设置此属性，可任意比例缩放。大视图模式  设置支持屏幕适配
        webSettings.setUseWideViewPort(true);
        //和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setLoadWithOverviewMode(true);
        //是否可以缩放
        webSettings.setSupportZoom(true);
        //是否显示缩放按钮
        webSettings.setBuiltInZoomControls(false);
        webSettings.setAllowFileAccess(true);
        //设置js可以直接打开窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//        webSettings.setLoadsImagesAutomatically(true);
//        webSettings.setDefaultTextEncodingName("utf-8");
//        webSettings.setPluginState(WebSettings.PluginState.ON);
        //解决图片不显示,视频无法播放问题问题
        webSettings.setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW);
//        webSettings.setDisplayZoomControls(true);
        //会导致有的点击事件无法响应
//        webSettings.setSupportMultipleWindows(true);
//        webSettings.setBlockNetworkImage(false);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        vm.bind.comWeb.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                onBackPressed();
//                return false;
//            }
//        });
    }

    private void setWebviewClient() {
        comWeb.setWebChromeClient(new WebChromeClient());
        comWeb.setWebViewClient(new WebViewClient());
    }

    private void initListener() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}p{word-break:break-all;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    @Override
    public void onBackPressed() {

        if (comWeb.canGoBack()) {
            comWeb.goBack();
            return;
        } else {
            super.onBackPressed();
        }

        if (comWeb != null) {
            comWeb.clearCache(true);
            comWeb.clearHistory();
        }
    }
}