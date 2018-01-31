package yxd.design_mode.structure.decorate.component;

import android.util.Log;

/**
 * Created by asus on 2017/12/17.
 */
/*
组件具体实现类（ConcreteComponent）

被装饰的具体对象，在这里就是被教授武学的具体的武侠，他就是杨过
 */
public class YangGuo extends Swordsman {
    @Override
    public void attackMagic() {
        //杨过初始的武学是全真剑法
        Log.d("Test", "杨过使用全真剑法");
    }
}
