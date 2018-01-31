package yxd.design_mode.behavior.mediator.mediator;

import yxd.design_mode.behavior.mediator.colleague.Emei;
import yxd.design_mode.behavior.mediator.colleague.Shaolin;
import yxd.design_mode.behavior.mediator.colleague.United;
import yxd.design_mode.behavior.mediator.colleague.Wudang;

/**
 * Created by asus on 2017/12/17.
 */
/*
武林盟主需要了解所有的门派，所以需要用setter来持有武当、峨眉和少林的引用。
notice方法会根据不同门派发来的消息，转而通知给其他的门派。
比如武当发来的消息，武林盟主就会将消息通知给少林。
 */
public class Champions extends WulinAlliance {

    private Wudang wudang;
    private Shaolin shaolin;
    private Emei emei;

    public void setWudang(Wudang wudang) {
        this.wudang = wudang;
    }

    public void setEmei(Emei emei) {
        this.emei = emei;
    }

    public void setShaolin(Shaolin shaolin) {
        this.shaolin = shaolin;
    }

    @Override
    public void notice(String message, United united) {
        if (united == wudang) {
            shaolin.getNotice(message);
        } else if (united == emei) {
            shaolin.getNotice(message);
        } else if (united == shaolin) {
            wudang.getNotice(message);
            emei.getNotice(message);
        }
    }

}
