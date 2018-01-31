package yxd.android.case_mat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.android.R;
/*

①：运行这段代码，打开DDMS，选择当前APP的包名，
点击左边绿液箱子开始追踪，进行屏幕翻转操作，然后点击右边绿液箱子停止此次追踪。

②：导出hprof文件，重命名要在导出后进行，不要在导出文件之前进行重命名！

③：跳转到“C:\Users\asus\AppData\Local\Android\sdk\platform-tools”目录下，打开cmd，
运行这样的语句：hprof-conv E:\mat\test.hprof E:\mat\test1.hprof

④：用MAT打开生成的hprof文件，接下来的分析步骤记录在ReadMe.doc中
 */
public class MATActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mat);
        /*
        非静态内部类的长生命周期实例
         */
        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(60 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
