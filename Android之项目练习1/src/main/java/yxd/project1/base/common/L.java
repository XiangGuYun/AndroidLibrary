package yxd.project1.base.common;

import android.util.Log;

/**
 * Created by asus on 2017/12/20.
 */

public class L {

    public static boolean isEnabled = true;

    public static void d(String log){
        if(isEnabled){
            Log.d("Test", log);
        }
    }

    public static void e(String log){
        if(isEnabled){
            Log.e("Test", log);
        }
    }


}
