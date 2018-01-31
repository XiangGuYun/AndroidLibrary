package yxd.design_mode.creation.singleton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;

public class SingletonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton);
        //静态内部类单例
        StaticInnerSingleton.getInstance().test();
        //枚举单例
        EnumSingleton.INSTANCE.test();
        //双重检查锁单例
        DCLSingleton.getInstance().test();
    }
}
