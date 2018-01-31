package yxd.handler.case1;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import yxd.handler.R;

public class MainActivity extends AppCompatActivity {

    static Handler workHandler;
    static Handler uiHandler = new Handler();
    HandlerThread thread;
    WeakReference<MainActivity> activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = new WeakReference<>(this);

        thread = new HandlerThread("test", Process.THREAD_PRIORITY_DEFAULT);
        thread.start();
        //传入HandlerThread的Looper，那么handleMessage内执行在HandlerThread所在线程test
        workHandler = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 0x123){
                    Log.d("Test", "currentThread is"+Thread.currentThread().getName());
                    //通过runOnUiThread回调到主线程
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity.get(),
                                    "收到了消息1", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //通过主线程的Handler回调到主线程
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Test", "currentThread is"+
                                    Thread.currentThread().getName());
                            Toast.makeText(activity.get(),
                                    "收到了消息2", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
    }

    public void btnClick(View view) {
        workHandler.sendEmptyMessage(0x123);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.getLooper().quit();
        workHandler.removeCallbacksAndMessages(null);
    }
}
