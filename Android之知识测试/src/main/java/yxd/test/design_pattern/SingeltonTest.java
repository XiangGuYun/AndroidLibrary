package yxd.test.design_pattern;

import yxd.test.L;

/**
 * Created by asus on 2018/1/8.
 */

public class SingeltonTest{

    public static void main(){
        EnumSingleton.INSTANCE.info();
        StaticInnerSingleton.getInstance().info();
        DCLSingleton.getInstance().info();
    }

}

/**
 * 测试1：写出枚举单例并在main方法中调用test()
 */
enum EnumSingleton{
    INSTANCE;
    public void info(){
        L.d("我是枚举单例");
    }
}
/**
 * 测试2：写出静态内部类单例并在main方法中调用test()
 */
class StaticInnerSingleton{

    private StaticInnerSingleton(){}

    public static InnerClass getInstance(){
        return InnerClass.innerClass;
    }

    protected static class InnerClass{
        private static InnerClass innerClass = new InnerClass();
        private InnerClass(){}
        public void info(){
            L.d("我是静态内部类单例");
        }
    }
}

/**
 * 测试3：写出DCL单例并在main方法中调用test()
 */
class DCLSingleton{
    private volatile static DCLSingleton singleton;
    private DCLSingleton(){}
    public static DCLSingleton getInstance(){
        if(singleton==null){
            synchronized (DCLSingleton.class){
                if(singleton==null)
                    singleton = new DCLSingleton();
            }
        }
        return singleton;
    }
    public void info(){
        L.d("我是双重检查锁单例");
    }
}

