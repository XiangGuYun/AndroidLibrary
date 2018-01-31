package yxd.quick_develop.base.common;

import android.os.Message;

/**
 * Created by asus on 2016/7/27.
 */
public class MsgUtils {

    private Message message;

    public MsgUtils(){
        message = Message.obtain();
    }

    public <T> MsgUtils obj(T t){
        message.obj = t;
        return this;
    }

    public String str(){
        return (String) message.obj;
    }

    public MsgUtils what(int what){
        message.what = what;
        return this;
    }

    public Message end(){
        return message;
    }

}
