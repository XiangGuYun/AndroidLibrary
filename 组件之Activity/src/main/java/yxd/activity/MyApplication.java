package yxd.activity;

import android.app.Application;

import yxd.activity.base.network.VolleyUtils;

/**
 * Created by asus on 2017/12/21.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtils.init(this);
    }
}
