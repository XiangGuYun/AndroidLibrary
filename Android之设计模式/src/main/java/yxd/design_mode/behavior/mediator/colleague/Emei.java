package yxd.design_mode.behavior.mediator.colleague;

/**
 * Created by asus on 2017/12/17.
 */

import yxd.design_mode.behavior.mediator.mediator.WulinAlliance;

/**
 * 具体同事类（峨眉派）
 */
/*
武当、峨眉和少林类都有两个方法，其中getNotice方法是自有方法，
对于其他的门派（同事类）和武林联盟（中介者）没有依赖，只是用来接收武林盟主的通知。
sendAlliance方法则是依赖方法，它必须通过武林盟主才能完成行为。
 */
public class Emei extends United {
    public Emei(WulinAlliance wulinAlliance) {
        super(wulinAlliance);
    }
    public void sendAlliance(String message) {
        wulinAlliance.notice(message, Emei.this);
    }
    public void getNotice(String message) {
        System.out.println("峨眉收到消息：" + message);
    }
}
