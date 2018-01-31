package yxd.design_mode.structure.proxy.proxy;

import yxd.design_mode.structure.proxy.subject.IShop;

/**
 * Created by asus on 2017/12/17.
 */

public class Purchasing implements IShop {
    private IShop mShop;
    public Purchasing(IShop shop){
        mShop=shop;
    }
    @Override
    public void buy() {
        mShop.buy();
    }
}
