package yxd.design_mode.behavior.mediator.mediator;

import yxd.design_mode.behavior.mediator.colleague.United;

/**
 * Created by asus on 2017/12/17.
 */
/*
抽象中介者角色

首先我们创建抽象中介者类，在这个例子中，它是一个武林联盟类
 */
public abstract class WulinAlliance {
    //notice方法用于向门派发送通知，其中United为抽象同事类也就是门派类
    public abstract void notice(String message, United united);
}
