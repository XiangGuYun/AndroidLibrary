package yxd.design_mode.structure.decorate.decorator;

import yxd.design_mode.structure.decorate.component.Swordsman;

/**
 * Created by asus on 2017/12/17.
 */
/*
抽象装饰者（Decorator）

抽象装饰者保持了一个对抽象组件的引用，方便调用被装饰对象中的方法。
在这个例子中就是武学前辈要持有武侠的引用，方便教授他武学并“融会贯通”
 */
public abstract class Master extends Swordsman {
    private Swordsman mSwordsman;
    public Master(Swordsman mSwordsman){
        this.mSwordsman=mSwordsman;
    }
    @Override
    public void attackMagic() {
        mSwordsman.attackMagic();
    }
}
