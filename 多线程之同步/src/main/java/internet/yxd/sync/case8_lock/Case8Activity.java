package internet.yxd.sync.case8_lock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
synchronized的缺陷

在什么情况下获得锁的线程会释放锁？
1.获取锁的线程执行完了该代码块，然后线程释放对锁的占有；
2.线程执行发生异常时，JVM会让线程自动释放锁。
因此，一旦线程被阻塞那么就会影响代码的执行效率。
 */
/*
Lock的优势
1.可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间或者能够响应中断）
2.多线程读写文件时，读写互斥，写写互斥，但是读读不互斥，Lock可以实现这种需求。
 */
/*
Lock与synchronize的的区别
1.Lock是一个Java类，synchronized是Java的关键字。
2.synchronized不需要手动释放锁，Lock必须手动释放锁，否则可能会产生死锁。
 */
public class Case8Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case8);

        final Outputter1 output = new Outputter1();

        new Thread() {
            public void run() {
                output.output("线程1");
            }
        }.start();

        new Thread() {
            public void run() {
                output.output("线程2");
            }
        }.start();

    }
}
