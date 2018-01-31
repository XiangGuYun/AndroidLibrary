package internet.yxd.sync.case2_2sync_method;

import android.util.Log;

/**
 * Created by asus on 2017/12/28.
 */

class Example
{
    public synchronized void execute()
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
            Log.d("Test","Hello: " + i);
        }
    }

    public synchronized void execute2()
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
            Log.d("Test","World: " + i);
        }
    }

}
