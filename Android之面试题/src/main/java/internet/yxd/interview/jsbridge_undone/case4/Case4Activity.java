package internet.yxd.interview.jsbridge_undone.case4;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.util.HashMap;
import java.util.Set;

import internet.yxd.interview.R;
/*
方式3：通过WebChromeClient的onJsAlert()、onJsConfirm()、onJsPrompt()方法
回调拦截JS对话框alert()、confirm()、prompt()消息
在JS中，有三个常用的对话框方法：#见图片JsAlert.png
 */
/*
方式3的原理：Android通过 WebChromeClient 的onJsAlert()、onJsConfirm()、onJsPrompt()方法
回调分别拦截JS对话框 （即上述三个方法），得到他们的消息内容，然后解析即可。
 */
/*
下面的例子将用拦截 JS的输入框（即prompt()方法）说明 ：

常用的拦截是：拦截 JS的输入框（即prompt（）方法）
因为只有prompt()可以返回任意类型的值，操作最全面方便、更加灵活；
而alert()对话框没有返回值；confirm()对话框只能返回两种状态（确定 / 取消）两个值
 */
/*
步骤1：加载JS代码，如下：
case4.html
当使用mWebView.loadUrl("file:///android_asset/case4.html"加载了上述JS代码后，
就会触发回调onJsPrompt()，具体如下：

如果是拦截警告框（即alert()），则触发回调onJsAlert()；
如果是拦截确认框（即confirm()），则触发回调onJsConfirm()；
 */
/*
步骤2：在Android通过WebChromeClient复写onJsPrompt()
 */
public class Case4Activity extends AppCompatActivity {
    WebView mWebView;
    private WebChromeClient client;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case7);
        mWebView = findViewById(R.id.webview);

        WebSettings webSettings = mWebView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

// 先加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/case4.html");

        initClient();
        mWebView.setWebChromeClient(client);
    }

    private void initClient() {
        client = new WebChromeClient() {
            // 拦截输入框(原理同方式2)
            // 参数message:代表promt（）的内容（不是url）
            // 参数result:代表输入框的返回值
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                // 根据协议的参数，判断是否是所需要的url(原理同方式2)
                // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
                //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）

                Uri uri = Uri.parse(message);
                // 如果url的协议 = 预先约定的 js 协议
                // 就解析往下解析参数
                if ( uri.getScheme().equals("js")) {

                    // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                    // 所以拦截url,下面JS开始调用Android需要的方法
                    if (uri.getAuthority().equals("webview")) {

                        //
                        // 执行JS所需要调用的逻辑
                        System.out.println("js调用了Android的方法");
                        // 可以在协议上带有参数并传递到Android上
                        HashMap<String, String> params = new HashMap<>();
                        Set<String> collection = uri.getQueryParameterNames();

                        //参数result:代表消息框的返回值(输入值)
                        result.confirm("js调用了Android的方法成功啦");
                    }
                    return true;
                }
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            // 通过alert()和confirm()拦截的原理相同，此处不作过多讲述

            // 拦截JS的警告框
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            // 拦截JS的确认框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }
        };
    }
}
