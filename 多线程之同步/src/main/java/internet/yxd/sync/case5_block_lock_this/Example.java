package internet.yxd.sync.case5_block_lock_this;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    private Object object = new Object();

    public void execute()
    {
        Log.d("Test", "线程1的外面代码上");
        synchronized (this)
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
        Log.d("Test", "线程1的外面代码下");

    }

    public void execute2()
    {
        Log.d("Test", "线程2的外面代码上");
        synchronized (this)
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
        Log.d("Test", "线程2的外面代码下");

    }

}
