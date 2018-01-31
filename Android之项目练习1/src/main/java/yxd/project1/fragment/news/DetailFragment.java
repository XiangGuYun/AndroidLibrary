package yxd.project1.fragment.news;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import yxd.project1.R;
import yxd.project1.base.common.L;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.context.MainActivity;
import yxd.project1.context.MyApplication;
import yxd.project1.network.NetWorkUtils;

/**
 * Created by asus on 2018/1/2.
 */

public class DetailFragment extends BaseFragment {


    private String webUrl="";
    private LinearLayout ll_web;
    private WebView webView;
    private static final int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
    private WebViewClient webViewClient;
    private ProgressDialog progressDialog;
    private WebSettings settings;
    private List<String> urls = new ArrayList<>();
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreateView(View view) {
        ((MainActivity)getActivity()).getBottomMenu().setVisibility(View.GONE);
        ll_web=id(R.id.ll_web);
        //progressDialog = new ProgressDialog(getActivity());
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        webView = new WebView(getActivity());
        webView.setLayoutParams(new LinearLayout.LayoutParams(MATCH, MATCH));
        ll_web.addView(webView);
        /*
        设置网页浏览器客户端
         */
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //tv_url.setText(title);
                super.onReceivedTitle(view, title);
            }
        });
        /*
        设置网页视图客户端
         */
        initWebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl(webUrl);
        //progressDialog.show();
         /*
        设置支持JS
         */
        webView.getSettings().setJavaScriptEnabled(true);
        settings = webView.getSettings();
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);//开启DOM缓存，关闭的话H5自身的一些操作是无效的
        /*
        设置缓存模式
         */
        if (NetWorkUtils.isNetworkConnected(getActivity())) {//判断是否有网络链接
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
        }
        /*
        把图片加载放在最后来加载渲染
         */
        settings.setBlockNetworkImage(true);
        /*
        设置渲染优先级
         */
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        /*
        支持多窗口
         */
        settings.setSupportMultipleWindows(true);
        /*
        开启 DOM storage API 功能
         */
        settings.setDomStorageEnabled(true);
        /*
        开启 Application Caches 功能
         */
        settings.setAppCacheEnabled(true);
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键
                        urls.remove(webView.getUrl());
                        L.e("测量栈大小"+urls.size());
                        if(urls.size()!=0){
                            webView.goBack();//后退
                            return true;    //已处理
                        }else {
                            //((MainActivity)getActivity()).getFragmentUtils().remove(DetailFragment.this);
//                            MainActivity activity = (MainActivity)getActivity();
//                            activity.getFragmentUtils().switchFragment(activity.newsFragmnet);
                            return false;
                        }
                    }
                }
                return false;
            }
        });
        /*
        设置下载监听
         */
        webView.setDownloadListener(new MyDownloadListener());
    }

    private void initWebViewClient() {
        webViewClient = new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                //开始加载
                super.onLoadResource(view, url);
//                Log.d("Test", "onLoadResource "+url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url); //设置不用系统浏览器打开,直接显示在当前Webview
//                Log.d("Test", "shouldOverrideUrlLoading "+url);
                return false;
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.loadUrl("file:///android_asset/error.html");
                super.onReceivedError(view, errorCode, description, failingUrl);

            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                L.e("这行行了对话");
                super.onPageStarted(view, url, favicon);
//                Log.d("Test", "onPageStarted "+url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //progressDialog.dismiss();
                super.onPageFinished(view, url);
                if(!urls.contains(url)){
                    urls.add(url);
                    L.d("添加url到栈中");
                }
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url)
            {
//                Log.d("Test", "shouldInterceptRequest "+url);

                if (url.contains("[tag]"))
                {
                    String localPath = url.replaceFirst("^http.*[tag]\\]", "");
                    try
                    {
                        InputStream is = MyApplication.getContext().getAssets().open(localPath);
                        Log.d("Test", "shouldInterceptRequest: localPath " + localPath);
                        String mimeType = "text/javascript";
                        if (localPath.endsWith("css"))
                        {
                            mimeType = "text/css";
                        }
                        return new WebResourceResponse(mimeType, "UTF-8", is);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        return null;
                    }
                }
                else
                {
                    return null;
                }

            }
        };
    }

    /*
   自定义的WebView下载监听器
    */
    class MyDownloadListener implements DownloadListener {


        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                    String mimetype, long contentLength) {

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public void onDestroy() {
        if( webView!=null) {
            webView.setVisibility(View.GONE);
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }
}
