package internet.yxd.interview.jsbridge_undone.case2;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import internet.yxd.interview.R;
/*
方式1：通过 WebView的addJavascriptInterface()进行对象映射
步骤1：定义一个与JS对象映射关系的Android类：AndroidToJS
步骤2：将需要调用的JS代码以.html格式放到src/main/assets文件夹里
步骤3：在Android里通过WebView设置Android类与JS代码的映射
 */
public class Case2Activity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case5);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        // 设置与JS交互的权限
        webSettings.setJavaScriptEnabled(true);

        // 通过addJavascriptInterface()将Java对象映射到JS对象
        //参数1：Javascript对象名
        //参数2：Java对象名
        webView.addJavascriptInterface(new AndroidToJS(), "test");//AndroidtoJS类对象映射到js的test对象

        // 加载JS代码
        // 格式规定为:file:///android_asset/文件名.html
        webView.loadUrl("file:///android_asset/test1.html");
    }
}
