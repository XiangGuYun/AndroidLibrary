package internet.yxd.interview.java_wait.case2;

import android.util.Log;

/**
 * Created by asus on 2017/12/29.
 */

public class OutputRunnable implements Runnable{

    private int num;
    private Object lock;

    public OutputRunnable(int num, Object lock) {
        this.num = num;
        this.lock = lock;
    }

    @Override
    public void run() {
        try{
            while (true){
                synchronized (lock){
                    //当前持有锁的线程唤醒其它等待的线程
                    lock.notifyAll();
                    Log.d("Test", Thread.currentThread().getName()+"唤醒了其它线程");
                    Thread.sleep(1000);
                    //让当前的线程处于等待状态并释放锁
                    lock.wait();
                    Log.d("Test", Thread.currentThread().getName()+"处于等待状态");
                    Thread.sleep(1000);
                }
            }
        }catch (InterruptedException e){

        }

    }
}
