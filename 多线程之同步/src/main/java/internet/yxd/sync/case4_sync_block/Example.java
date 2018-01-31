package internet.yxd.sync.case4_sync_block;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    private Object object = new Object();

    public void execute()
    {
        /*
        等同于同步方法
        object是Example中的一个对象
        线程执行到这么代码块时，会获得Example的对象锁，致使其它线程无法同步执行
         */
        synchronized (object)
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
        synchronized (object)
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
