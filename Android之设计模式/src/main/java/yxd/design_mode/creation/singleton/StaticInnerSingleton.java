package yxd.design_mode.creation.singleton;

import android.util.Log;

/**
 * Created by asus on 2018/1/8.
 */
/*
静态内部类单例
 */
public class StaticInnerSingleton {

    private StaticInnerSingleton(){

    }

    public static InnerClass getInstance(){
        return InnerClass.instance;
    }

    /*
    注意静态内部类的访问权限不能是private，至少是protected
     */
    protected static class InnerClass{
        //构造方法必须私有
        private InnerClass(){}
        //静态实例也必须私有
        private static InnerClass instance = new InnerClass();
        /*
        为了父类调用方法的访问权限至少是protected
         */
        protected void test(){
            Log.d("Test", "我是静态内部类单例");
        }

    }

}
