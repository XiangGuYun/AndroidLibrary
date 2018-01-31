package internet.yxd.sync.case4_sync_block;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
synchronized代码块

以下两种方式的效果是一样的
public synchronized void test(){
    //执行代码
}
public void test(){
    synchronized(this){
        //执行代码
    }
}

当一个方法中只有几行代码会涉及到线程同步问题，那么synchronized块会比synchronized方法更加细粒度地控制
多个线程的访问，因为只有synchronized块中的内容不能同时被多个线程所访问，
方法中的其他语句仍然可以同时被多个线程所访问（包括synchronized块之前的和之后的）。
 */
public class Case4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case4);

        Example example = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example);

        t1.start();
        t2.start();

    }
}
