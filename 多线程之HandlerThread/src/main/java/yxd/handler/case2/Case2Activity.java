package yxd.handler.case2;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import yxd.handler.R;

import static yxd.handler.case2.DownloadUtils.downloadBitmap;

public class Case2Activity extends AppCompatActivity {
    private static HandlerThread thread;
    private static final String URL =
            "https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png";
    private MyHandler myHandler;
    private ImageView imageView;

    static class MyHandler extends Handler{
        private static Bitmap bitmap;
        private WeakReference<Activity> reference;
        private WeakReference<ImageView> ivReference;

        //使用外部类的引用使用弱引用，通过构造方法传入
        public MyHandler(Looper looper, Activity activity, ImageView imageView){
            super(looper);
            reference = new WeakReference<>(activity);
            ivReference = new WeakReference<>(imageView);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==0x123){
                bitmap = downloadBitmap(URL);
                reference.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(null!=bitmap){
                            ivReference.get().setImageBitmap(bitmap);
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        imageView = findViewById(R.id.iv);
        thread = new HandlerThread("test", 0);
        thread.start();
        myHandler = new MyHandler(thread.getLooper(), this, imageView);

    }

    @Override
    protected void onDestroy() {
        MyHandler.bitmap.recycle();
        //退出
        myHandler.getLooper().quit();
        //移除回调和消息队列
        myHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    public void download(View view) {
        myHandler.sendEmptyMessage(0x123);
    }


}
