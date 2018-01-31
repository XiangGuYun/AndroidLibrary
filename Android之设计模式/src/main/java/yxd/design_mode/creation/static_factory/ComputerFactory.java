package yxd.design_mode.creation.static_factory;

/**
 * Created by asus on 2017/12/19.
 */
/*
创建工厂类

接下来创建一个工厂类，它提供了一个静态方法createComputer用来生产电脑。你只需要传入你想生产的电脑的品牌，
它就会实例化相应品牌的电脑对象
 */
public class ComputerFactory {
    public static Computer createComputer(String type){
        Computer mComputer=null;
        switch (type) {
            case "lenovo":
                mComputer=new LenovoComputer();
                break;
            case "hp":
                mComputer=new HpComputer();
                break;
            case "asus":
                mComputer=new AsusComputer();
                break;
        }
        return mComputer;
    }
}
