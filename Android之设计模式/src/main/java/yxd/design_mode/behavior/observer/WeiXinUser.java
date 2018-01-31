package yxd.design_mode.behavior.observer;

import android.util.Log;

/**
 * Created by asus on 2017/12/17.
 */
/*
具体观察者
 */
public class WeiXinUser implements Observer {

    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        Log.d("Test", name+"---------"+message);
    }
}
