package yxd.design_mode.creation.singleton;

import android.util.Log;

/**
 * Created by asus on 2018/1/8.
 */

public enum EnumSingleton {

    INSTANCE;

    public void test(){
        Log.d("Test", "我是枚举单例");
    }

}
