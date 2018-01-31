package internet.yxd.sync.case8_lock;

import android.util.Log;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by asus on 2017/12/28.
 */

class Outputter1 {
    private Lock lock = new ReentrantLock();// 锁对象
    public void output(String name) {
        // TODO 线程输出方法
        lock.lock();// 得到锁
        try {
            for(int i = 0; i < name.length(); i++) {
                Log.d("Test", name.charAt(i)+"");
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
