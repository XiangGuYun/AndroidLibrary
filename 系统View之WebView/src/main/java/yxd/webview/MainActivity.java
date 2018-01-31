package yxd.webview;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.net.http.SslError;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private WebView webView;
    private TextView tv_url;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        webView.loadUrl("file:///android_asset/test.html");
        /*
        设置网页浏览器客户端
         */
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                tv_url.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
        /*
        设置网页视图客户端
         */
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url); //设置不用系统浏览器打开,直接显示在当前Webview
                return true;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                //super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
         /*
        设置支持JS
         */
        webView.getSettings().setJavaScriptEnabled(true);
        /*
        设置下载监听
         */
        webView.setDownloadListener(new MyDownloadListener());


    }

    /*
    自定义的WebView下载监听器
     */
    class MyDownloadListener implements DownloadListener {


        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                    String mimetype, long contentLength) {
            if(url.endsWith(".apk"))
                new HttpThread(url).start();
        }
    }

    @Override
    protected void onDestroy() {
        /*
        释放WebView资源
         */
        if(webView!=null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }

    /*
    返回按钮
     */
    public void back(View view) {
        finish();
    }

    /*
    刷新按钮
     */
    public void refresh(View view) {
        webView.reload();
    }

    private void initView() {
        linearLayout = findViewById(R.id.ll);
        tv_url = findViewById(R.id.tv_url);
        webView = new WebView(this);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        linearLayout.addView(webView);
    }
}
