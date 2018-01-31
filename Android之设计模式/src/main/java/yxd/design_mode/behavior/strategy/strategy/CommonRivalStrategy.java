package yxd.design_mode.behavior.strategy.strategy;

import yxd.design_mode.behavior.strategy.strategy.FightingStrategy;

/**
 * Created by asus on 2017/12/17.
 */

public class CommonRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        System.out.println("遇到了普通的对手，张无忌使用圣火令神功");
    }
}
