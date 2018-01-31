package yxd.design_mode.behavior.mediator.colleague;

import yxd.design_mode.behavior.mediator.mediator.WulinAlliance;

/**
 * Created by asus on 2017/12/17.
 */
/*
门派类（抽象同事类）会在构造方法中得到武林联盟类（抽象中介者类）
 */
public abstract class United {

    protected WulinAlliance wulinAlliance;

    public United(WulinAlliance wulinAlliance){
        this.wulinAlliance=wulinAlliance;
    }

}
