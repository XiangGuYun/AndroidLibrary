package internet.yxd.sync.case9_rwlock;

import android.util.Log;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by asus on 2017/12/28.
 */

public class LockData {
    private int data;// 共享数据
    private ReadWriteLock rwl = new ReentrantReadWriteLock();//读写锁
    public void set(int data) {
        rwl.writeLock().lock();// 取到写锁
        try {
            Log.d("Test", Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
            Log.d("Test", Thread.currentThread().getName() + "写入" + this.data);
        } finally {
            rwl.writeLock().unlock();// 释放写锁
        }
    }
    public void get() {
        rwl.readLock().lock();// 取到读锁
        try {
            Log.d("Test", Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("Test", Thread.currentThread().getName() + "读取" + this.data);
        } finally {
            rwl.readLock().unlock();// 释放读锁
        }
    }
}
