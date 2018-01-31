package internet.yxd.interview.jsbridge_undone.case3;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Set;

import internet.yxd.interview.R;
/*
方式2：通过 WebViewClient 的方法shouldOverrideUrlLoading()回调拦截 url
具体原理：
Android通过 WebViewClient 的回调方法shouldOverrideUrlLoading()拦截 url
解析该 url 的协议
如果检测到是预先约定好的协议，就调用相应方法
即JS需要调用Android的方法
 */
/*
步骤1：在JS约定所需要的Url协议
JS代码：test2.html
当该JS通过Android的mWebView.loadUrl("file:///android_asset/test2.html")加载后，
就会回调shouldOverrideUrlLoading
 */
/*
步骤2：在Android通过WebViewClient复写shouldOverrideUrlLoading
 */
/*

优点：不存在方式1的漏洞；
缺点：JS获取Android方法的返回值复杂。

如果JS想要得到Android方法的返回值，只能通过 WebView的loadUrl去执行 JS 方法把返回值传递回去，相关的代码如下：
// Android：MainActivity.java
mWebView.loadUrl("javascript:returnResult(" + result + ")");

// JS：javascript.html
function returnResult(result){
    alert("result is" + result);
}
 */
public class Case3Activity extends AppCompatActivity {

    WebView mWebView;
    private WebViewClient client;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case6);

        mWebView = findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 步骤1：加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/test2.html");

        initClient();

// 复写WebViewClient类的shouldOverrideUrlLoading方法
        mWebView.setWebViewClient(client);

    }

    private void initClient() {
        new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                // 步骤2：根据协议的参数，判断是否是所需要的url
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
                Uri uri = Uri.parse(url);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if ( uri.getScheme().equals("js")) {
                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {
                        //  步骤3：
                        // 执行JS所需要调用的逻辑
                        Log.d("Test", "js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                    }

                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
    }
}
