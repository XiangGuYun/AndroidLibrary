package internet.yxd.sync.case5_block_lock_this;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
this表示类的一个实例
如果锁住的this，同步代码块和同步方法具备一致的互斥性。
但是同步代码块外面的代码不受限制。
 */
public class Case5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case5);

        Example example = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example);

        t1.start();
        t2.start();

    }
}
