package internet.yxd.interview.java_wait.case2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.interview.R;

public class Case2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        /*对象锁*/
        Object lock = new Object();
        /*
        线程1
         */
        Thread thread1 = new Thread(new OutputRunnable(1, lock));
        /*
        线程2
         */
        Thread thread2 = new Thread(new OutputRunnable(2, lock));
        thread1.setName("线程1");
        thread2.setName("线程2");
        thread1.start();
        thread2.start();

    }
}
