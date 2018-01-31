package internet.yxd.interview.java_wait.case1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.interview.R;

/*
1. 正如Java内任何对象都能成为锁(Lock)一样，任何对象也都能成为条件队列(Condition queue)。
而这个对象里的wait(), notify()和notifyAll()则是这个条件队列的固有(intrinsic)的方法。
2. 一个对象的固有锁和它的固有条件队列是相关的，为了调用对象X内条件队列的方法，你必须获得对象X的锁。
这是因为等待状态条件的机制和保证状态连续性的机制是紧密的结合在一起的。
 */
/*
要在某个对象上执行wait，notify，先必须锁定该对象，而对应的状态变量也是由该对象锁保护的。
 */
public class Case1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case1);

          /*
         下面两个方法都会报以下异常
         Caused by: java.lang.IllegalMonitorStateException:
         object not locked by thread before wait()
         */
//        exception1();
//        exception2();

        /*
        正确写法
         */
        Object obj = new Object();
        synchronized (obj){//必须获取到对象的锁才能使用wait和notify
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.notifyAll();
        }
    }
    /*
    错误写法1
     */
    private void exception1() {
        Object obj = new Object();
        //错误原因：没有在写在同步代码块中，也没有获取到对象的锁
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        obj.notifyAll();
    }
    /*
    错误写法2
     */
    private void exception2() {
        Object obj = new Object();
        Object lock = new Object();
        //错误原因：尽管写在同步代码块中，但是获取到的锁不是需求对象的锁
        synchronized (lock) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj.notifyAll();
        }
    }
}
