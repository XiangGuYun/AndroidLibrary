package internet.yxd.sync.case6_block_lock_class;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
如果锁住的class，那么同步代码块的互斥性与静态同步方法的互斥性是一致的。
 */
public class Case6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case6);

        Example example = new Example();
        Example example1 = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example1);

        t1.start();
        t2.start();

    }
}
