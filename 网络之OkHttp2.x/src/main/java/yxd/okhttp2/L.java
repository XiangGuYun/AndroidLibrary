package yxd.okhttp2;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by asus on 2017/12/19.
 */

public class L {
    private static final String MY_TAG = "Test";
    private static boolean debug = true;

    public static void e(String msg){
        if(debug){
            Log.d(MY_TAG, msg);
        }
    }
}
