package yxd.design_mode.structure.flyweight;

/**
 * Created by asus on 2017/12/17.
 */
/*
抽象享元角色是一个商品接口，它定义了showGoodsPrice方法用来展示商品的价格
 */
public interface IGoods {
    void showGoodsPrice(String name);
}
