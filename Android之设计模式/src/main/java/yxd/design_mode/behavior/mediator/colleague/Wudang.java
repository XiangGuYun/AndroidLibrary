package yxd.design_mode.behavior.mediator.colleague;

/**
 * Created by asus on 2017/12/17.
 */

import yxd.design_mode.behavior.mediator.mediator.WulinAlliance;

/**
 * 具体同事类（武当）
 */
public class Wudang extends United {
    public Wudang(WulinAlliance wulinAlliance) {
        super(wulinAlliance);
    }
    public void sendAlliance(String message) {
        wulinAlliance.notice(message, this);
    }
    public void getNotice(String message) {
        System.out.println("武当收到消息：" + message);
    }
}
