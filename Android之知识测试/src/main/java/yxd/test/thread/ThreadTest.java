package yxd.test.thread;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.lang.ref.WeakReference;


/**
 * Created by asus on 2018/1/8.
 */

public class ThreadTest extends Activity{

    private MyHandler myHandler;

    public void main(){
        HandlerThread thread = new HandlerThread("Test", 0);
        thread.start();
        myHandler = new MyHandler(ThreadTest.this, thread.getLooper());
    }

    @Override
    protected void onDestroy() {
        myHandler.getLooper().quit();
        myHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    /**
     * 测试1
     * 写出自定义的Handler静态内部类
     * 线程要求使用HandlerThread
     * 构造方法传入Activity
     * 并在onDestroy中清除队列
     */
    static class MyHandler extends Handler {
        private WeakReference<Activity> weakReference;

        public MyHandler(Activity activity, Looper looper){
            super(looper);
            weakReference = new WeakReference<Activity>(activity);
        }
    }

}
