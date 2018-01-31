package internet.yxd.sync.case1_have_nohave_sync;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    /*
    用一时间，只能有一个线程，来执行一个example的execute方法
     */
    public synchronized void execute()
    {
        for (int i = 0; i < 10; ++i)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Log.d("Test", "Hello: " + i);
        }
    }

}
