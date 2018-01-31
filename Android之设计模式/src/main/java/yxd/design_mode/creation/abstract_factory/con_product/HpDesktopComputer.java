package yxd.design_mode.creation.abstract_factory.con_product;

import yxd.design_mode.creation.abstract_factory.ab_product.DesktopComputer;

/**
 * Created by asus on 2017/12/19.
 */

public class HpDesktopComputer extends DesktopComputer {
    @Override
    public void start() {
        System.out.println("惠普台式电脑启动");
    }
}
