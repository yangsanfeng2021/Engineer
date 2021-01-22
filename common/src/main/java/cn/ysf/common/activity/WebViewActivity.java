package cn.ysf.common.activity;

import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.ysf.common.BaseConstant;
import cn.ysf.common.R;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.model.JsInterface;
import cn.ysf.common.view.HActionBar;

@Route(path = RoutePath.COMMON_ACTIVITY_WEB_VIEW)
public class WebViewActivity extends BaseActivity {

    private HActionBar webViewHActionBar;

    private ProgressBar webViewProgressBar;

    private WebView webView;

    private String url;
    private String testUrl = "https://mp.weixin.qq.com/s/gC5Xy3_syL8s9mHYc9ruVA";

    @Override
    protected int getLayoutId() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        return R.layout.activity_webview;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initWebSettings();
        webView.loadUrl("https://mp.weixin.qq.com/s/6aDEddBEugzRuTiaQPtLeQ");
        initListener();
    }

    private void init() {

        url = getIntent().getStringExtra(BaseConstant.WEB_URL);

        webViewHActionBar = findViewById(R.id.webViewHActionBar);
        webViewProgressBar = findViewById(R.id.webViewProgressBar);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
    }

    private void initWebSettings() {
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webView.addJavascriptInterface(new JsInterface(this), "webkit");
    }

    private void initListener() {
        webViewHActionBar.setOnActionBarClickListener(new HActionBar.OnActionBarClickListener() {
            @Override
            public boolean onActionBarClick(int function) {
                if (function == HActionBar.FUNCTION_BTN_IMG_BACK) {
                    if (webView != null && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onLongActionBarClick(int function) {

            }
        });
    }

    private final WebViewClient webViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
            super.onPageStarted(webView, s, bitmap);
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            addLocalJs(webView);
        }

        @Override
        public void onReceivedError(WebView webView, int i, String s, String s1) {
            super.onReceivedError(webView, i, s, s1);
        }
    };

    private final WebChromeClient webChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView webView, String title) {
            super.onReceivedTitle(webView, title);
            webViewHActionBar.setTitleText(title);
        }


        @Override
        public void onProgressChanged(WebView webView, int progress) {
            super.onProgressChanged(webView, progress);
            webViewProgressBar.setProgress(progress);
            if (progress == 100) {
                webViewProgressBar.setVisibility(View.GONE);
            }
        }

        @Override
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void addLocalJs(WebView view) {
        view.loadUrl("javascript:(function() {"
                + "         var obj =  $(\"body\").find('img');"
                + "         var imgLists = [];"
                + "         var reg = /^(https)(.)+(jpeg|png|gif)$/;"
                + "         for(var i=0; i<obj.length; i++) {"
                + "         if(obj.eq(i).attr(\"data-src\") == null || obj.eq(i).attr(\"data-src\") == \"\" || obj.eq(i).attr(\"data-src\") == undefined){"
                + "          }else{"
                + "             if(reg.test(obj.eq(i).attr(\"data-src\"))){"
                + "                 imgLists.push(obj.eq(i).attr(\"data-src\"));"
                + "              }"
                + "          }"
                + "         obj[i].onclick = function(){"
                + "                window.webkit.open(imgLists,this.src);"
                + "             }"
               /* + "         obj[i].addEventListener(\"touchstart\", function (e) {"
                + "                 timer = setTimeout(function () {"
                + "                 e.preventDefault();"
                + "                  LongPress();"
                + "                  }, 800);"
                + "              });"
                + "          obj[i].addEventListener(\"touchend\", function (e) {"
                + "                  clearTimeout(timer);"
                + "                  return false;"
                + "              });"
                + "           function LongPress(){"
                + "              window.webkit.onLongClick(this.src);"
                + "              }"*/
                + "         }"
                + "     })()");
    }
}
