package yxd.design_mode.structure.proxy.subject;

/**
 * Created by asus on 2017/12/17.
 */
/*
真实主题类（RealSubject）

这个购买者LiuWangShu也就是我，实现了IShop接口提供的 buy()方法
 */
public class LiuWangShu implements IShop {
    @Override
    public void buy() {
        System.out.println("购买");
    }
}
