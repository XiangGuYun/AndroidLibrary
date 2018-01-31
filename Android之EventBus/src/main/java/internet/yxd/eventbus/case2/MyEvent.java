package internet.yxd.eventbus.case2;

/**
 * Created by asus on 2017/12/17.
 */

public class MyEvent {
    private String msg;

    public MyEvent(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
