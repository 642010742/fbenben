package com.dwz.mvvmdemo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.dwz.library.App.AppConstants;
import com.dwz.library.baseView.BaseActivity;
import com.dwz.library.commonModel.TitleOptions;
import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.CommWebViewVModel;
import static android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

public class CommWebview extends BaseActivity<CommWebViewVModel> {

    @Override
    public TitleOptions title() {
        return new TitleOptions();
    }

    @Override
    public void typeTitle(TitleOptions titleOptions, Intent intent) {
        super.typeTitle(titleOptions, intent);
        String title = getIntent().getStringExtra(AppConstants.IntentKey.WEB_TITLE);
        titleOptions.setLeft_image(R.drawable.back);
        titleOptions.setCenter_title(title);
    }

    @Override
    public Class<CommWebViewVModel> getVMClass() {
        return CommWebViewVModel.class;
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_comm_webview;
    }

    @Override
    public void initViews() {

        String url = getIntent().getStringExtra(AppConstants.IntentKey.WEB_URL);
        initWebSetting();
        setWebviewClient();

        if (!TextUtils.isEmpty(url)) {
            if ((url.startsWith("http"))) {
                vm.bind.comWeb.loadUrl(url);
            } else {
                //解决6.0乱码问题
                vm.bind.comWeb.loadDataWithBaseURL(null, getHtmlData(url), "text/html", "utf-8", null);
            }
        }
    }

    //初始化websetting
    private void initWebSetting() {
        WebSettings webSettings = vm.bind.comWeb.getSettings();
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
        vm.bind.comWeb.setWebViewClient(new WebViewClient());
//        comWeb.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                if (request.getUrl().toString().startsWith("http")) {
//                    //false webview处理url是在wenbview内部执行
//                    return false;
//                }
//                //try-catch 转化url 有可能是打开本地app的url,如果没有安装app会发生崩溃
//                try {
//                    Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
//                    startActivity(intent);
//                } catch (Exception e) {
//                    Log.e("TAG", " Exception is ==== >>> " + e);
//                }
//                //true webview处理url是根据程序来执行
//                return true;
//            }
//
//        });
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

        if (vm.bind.comWeb.canGoBack()) {
            vm.bind.comWeb.goBack();
            return;
        } else {
            super.onBackPressed();
        }

        if (vm.bind.comWeb != null) {
            vm.bind.comWeb.clearCache(true);
            vm.bind.comWeb.clearHistory();
        }
    }
}