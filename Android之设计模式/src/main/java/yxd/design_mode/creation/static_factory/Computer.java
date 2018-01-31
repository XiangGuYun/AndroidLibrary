package yxd.design_mode.creation.static_factory;

/**
 * Created by asus on 2017/12/19.
 */
/*
创建抽象产品类

我们创建一个电脑的抽象产品类，他有一个抽象方法用于启动电脑
 */
public abstract class Computer {
    /**
     * 产品的抽象方法，由具体的产品类去实现
     */
    public abstract void start();
}
