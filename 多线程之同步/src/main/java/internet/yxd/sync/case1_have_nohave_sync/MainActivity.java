package internet.yxd.sync.case1_have_nohave_sync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;

/*
是否使用synchronized关键字的不同
如果不加synchronized关键字，则两个线程同时执行execute()方法，输出是两组并发的。
如果加上synchronized关键字，则会先输出一组0到9，然后再输出下一组，说明两个线程是顺次执行的。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        锁也可以理解为通行证，不能共享，只能独占或交接
         */
        Example example = new Example();

        /*
        如果在线程t2中传入example1，那么就不会产生同步效果
        因为两个example对象各有一把锁分别被t1和t2获取到
         */
        //Example example1 = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread1(example);

        t1.start();
        t2.start();

    }

}
