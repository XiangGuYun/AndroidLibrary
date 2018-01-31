package internet.yxd.sync.case7_block_lock_object;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    private Object object = new Object();

    public void execute()
    {
        synchronized (object)//线程1抢到了object的锁
        {
            for (int i = 0; i < 20; ++i)
            {
                try
                {
                    Thread.sleep((long) Math.random() * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Log.d("Test", "线程1: " + i);
            }

        }

    }

    public void execute2()
    {
        synchronized (object)//因为线程2没有object的锁所以只能等待线程1执行完同步代码块
        {
            for (int i = 0; i < 20; ++i)
            {
                try
                {
                    Thread.sleep((long) Math.random() * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Log.d("Test", "线程2: " + i);
            }

        }
    }

}
