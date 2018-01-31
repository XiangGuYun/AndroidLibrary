package internet.yxd.sync.case2_2sync_method;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import internet.yxd.sync.R;
/*
即便是两个方法：
执行结果永远是执行完一个线程的输出再执行另一个线程的。　

说明：
如果一个对象有多个synchronized方法，某一时刻某个线程已经进入到了某个synchronized方法，
那么在该方法没有执行完毕前，其他线程是无法访问该对象的任何synchronized方法的。

结论：
当synchronized关键字修饰一个方法的时候，该方法叫做同步方法。

Java中的每个对象都有一个锁（lock），或者叫做监视器（monitor），
当一个线程访问某个对象的synchronized方法时，将该对象上锁，其他任何线程都无法再去访问
该对象的synchronized方法了（这里是指所有的同步方法，而不仅仅是同一个方法），
直到之前的那个线程执行方法完毕后（或者是抛出了异常），才将该对象的锁释放掉，
其他线程才有可能再去访问该对象的synchronized方法。

注意这时候是给对象上锁，如果是不同的对象，则各个对象之间没有限制关系。
 */
public class Case2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);

        /*
        这个试验证明了对象锁会锁住单一对象的所有同步方法，
        如果一个线程获得了这个对象的锁，那么其他线程便无法同时执行这个对象的任何同步方法。
         */
        Example example = new Example();

        Thread t1 = new Thread1(example);
        Thread t2 = new Thread2(example);
         /*
        尝试在代码中构造第二个线程对象时传入一个新的Example对象，
        则两个线程的执行之间没有什么制约关系。
         */
        //Example example1 = new Example();
        //Thread t2 = new Thread2(example1);

        t1.start();
        t2.start();


    }

}
