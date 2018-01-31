package yxd.design_mode.creation.factory;

/**
 * Created by asus on 2017/12/19.
 */
/*
创建具体产品类

接着我们创建各个品牌的电脑，他们都继承了他们的父类Computer ，并实现了父类的start方法
 */
public class LenovoComputer extends Computer {
    @Override
    public void start() {
        System.out.println("联想电脑启动");
    }
}
