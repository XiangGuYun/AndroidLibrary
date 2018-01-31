package yxd.design_mode.structure.proxy.subject;

/**
 * Created by asus on 2017/12/17.
 */
/*
抽象主题类（Subject）

抽象主题类具有真实主题类和代理的共同接口方法，我想要代购，那共同的方法就是购买
 */
public interface IShop {
    //购买
    void buy();
}
