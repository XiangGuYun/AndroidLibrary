package yxd.design_mode.behavior.strategy.strategy;

import yxd.design_mode.behavior.strategy.strategy.FightingStrategy;

/**
 * Created by asus on 2017/12/17.
 */

public class WeakRivalStrategy implements FightingStrategy {
    @Override
    public void fighting() {
        System.out.println("遇到了较弱的对手，张无忌使用太极剑");
    }
}
