package yxd.design_mode.creation.abstract_factory.con_product;

import yxd.design_mode.creation.abstract_factory.ab_product.NotebookComputer;

/**
 * Created by asus on 2017/12/19.
 */

public class HpNotebookComputer extends NotebookComputer {
    @Override
    public void start() {
        System.out.println("惠普笔记本电脑启动");
    }
}
