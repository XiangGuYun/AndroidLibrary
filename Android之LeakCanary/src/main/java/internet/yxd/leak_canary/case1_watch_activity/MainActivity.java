package internet.yxd.leak_canary.case1_watch_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;

import internet.yxd.leak_canary.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LeakThread thread = new LeakThread();
        thread.start();
    }

    /*
    非静态内部类LeakThread持有外部类MainActivity的引用，
    LeakThread中做了耗时操作，导致MainActivity无法被释放
     */
    private class LeakThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(6*10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    在Activity的onDestroy中添加如下代码是不必要的。
    因为LeakCanary在调用install方法时会启动一个ActivityRefWatcher类，
    它用于自动监控Activity执行onDestroy方法之后是否发生内存泄露。
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        RefWatcher watcher = LeakApplication.getRefWatcher(this);
//        watcher.watch(this);
    }
}
