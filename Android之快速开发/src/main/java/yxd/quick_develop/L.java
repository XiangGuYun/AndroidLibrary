package yxd.quick_develop;

import android.util.Log;

/**
 * Created by asus on 2018/1/17.
 */

public class L {

    private static boolean flagAll=true;
    private static boolean flag1=true;
    private static boolean flag2=true;
    private static boolean flag3=true;
    private static boolean flag4=true;
    private static boolean flag5=true;
    private static boolean flag6=true;

    public static void t1(String text){
        if(flag1&&flagAll)
            Log.d("TAG1", text);
    }
    public static void t2(String text){
        if(flag2&&flagAll)
            Log.d("TAG2", text);
    }
    public static void t3(String text){
        if(flag3&&flagAll)
            Log.d("TAG3", text);
    }
    public static void t4(String text){
        if(flag4&&flagAll)
            Log.d("TAG4", text);
    }
    public static void t5(String text){
        if(flag5&&flagAll)
            Log.d("TAG1", text);
    }
    public static void t6(String text){
        if(flag6&&flagAll)
            Log.d("TAG6", text);
    }

}
