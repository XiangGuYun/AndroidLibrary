package internet.yxd.sync.case7_block_lock_object;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
线程1抢到了object的锁，那么只有当线程1执行完同步代码块释放了锁线程2才能得到object的锁。
如果两个object不是同一个对象那么就无法起到互斥效果。
 */
public class Case7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case7);

        Example example = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example);

        t1.start();
        t2.start();

    }
}
