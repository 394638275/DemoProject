package com.lew.mapleleaf.ui.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.lew.mapleleaf.R;
import com.lew.mapleleaf.ui.BaseActivity;
import com.lew.mapleleaf.utils.logger.Logger;

/**
 * Created by liuqian 16/6/11.
 */
public class BaseWebViewActivity extends BaseActivity {
    private WebView mWebView;

    @Override
    protected int addContentRes() {
        return R.layout.default_webview;
    }

    @Override
    protected void initView() {
        mWebView = (WebView) findViewById(R.id.default_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        mWebView.setWebChromeClient(new MyWebChromeClient());
        mWebView.loadUrl("file:///android_asset/demo.html");
    }

    @Override
    protected void initData() {

    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Logger.d(message);
            result.confirm();
            return true;
        }
    }
}
