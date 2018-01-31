package yxd.activity.base.context;

import android.os.Message;
import android.support.v4.app.Fragment;

import yxd.activity.base.common.MsgUtils;

/**
 * Created by asus on 2016/7/27.
 */
public class FragmentSubject extends Fragment {

    protected Observer ob = Observer.getObserver();


    protected void update(){

    }

    public MsgUtils msg(){
        return new MsgUtils();
    }

    public <T> Message msg(T t){
        return new MsgUtils().obj(t).end();
    }

    public Message msgWhat(int what){
        return new MsgUtils().what(what).end();
    }

    public <T> Message msg(int what, T t){
        return new MsgUtils().what(what).obj(t).end();
    }



}
