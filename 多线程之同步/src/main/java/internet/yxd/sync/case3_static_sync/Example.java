package internet.yxd.sync.case3_static_sync;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    /*
    给同步方法添加static关键字
     */
    public synchronized static void execute()
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

    /*
    给同步方法添加static关键字
     */
    public synchronized static void execute2()
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
