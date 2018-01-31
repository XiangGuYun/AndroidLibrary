package internet.yxd.sync.case9_rwlock;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Data {
    private int data;// 共享数据
    public synchronized void set(int data) {
        Log.d("Test", Thread.currentThread().getName() + "准备写入数据");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        Log.d("Test", Thread.currentThread().getName() + "写入" + this.data);
    }
    public synchronized void get() {
        Log.d("Test", Thread.currentThread().getName() + "准备读取数据");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Test", Thread.currentThread().getName() + "读取" + this.data);
    }
}
