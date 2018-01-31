package internet.yxd.eventbus.case3;

/**
 * Created by asus on 2017/12/21.
 */

class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
