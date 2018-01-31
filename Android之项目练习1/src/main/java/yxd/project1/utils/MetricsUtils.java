package yxd.project1.utils;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by asus on 2018/1/1.
 */

public class MetricsUtils {

    public static int getScreenWidth(FragmentActivity ctx){
        WindowManager manager = ctx.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(FragmentActivity ctx){
        WindowManager manager = ctx.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int[] getScreenMetrics(FragmentActivity ctx){
        int arr[] = new int[2];
        WindowManager manager = ctx.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        arr[0]=outMetrics.widthPixels;
        arr[1]=outMetrics.heightPixels;
        return arr;
    }

}
