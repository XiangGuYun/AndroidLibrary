package yxd.design_mode.creation.singleton;

import android.util.Log;

/**
 * Created by asus on 2018/1/8.
 */

public class DCLSingleton {
    //单例对象添加额外的volatile关键字
    private static volatile DCLSingleton singleton;

    private DCLSingleton(){

    }

    public static DCLSingleton getInstance(){
        //第一次非空判断
        if(singleton==null){
            //第二次在同步代码块中进行非空判断，锁住类
            synchronized (DCLSingleton.class){
                if(singleton==null){
                    singleton=new DCLSingleton();
                }
            }
        }
        return singleton;
    }

    public void test(){
        Log.d("Test", "我是DCL单例");
    }

}
