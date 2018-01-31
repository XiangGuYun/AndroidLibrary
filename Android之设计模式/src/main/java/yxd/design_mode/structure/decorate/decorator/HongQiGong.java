package yxd.design_mode.structure.decorate.decorator;

import android.util.Log;

import yxd.design_mode.structure.decorate.component.Swordsman;

/**
 * Created by asus on 2017/12/17.
 */

public class HongQiGong extends Master {
    public HongQiGong(Swordsman mSwordsman) {
        super(mSwordsman);
    }
    public void teachAttackMagic(){
        Log.d("Test", "洪七公教授打狗棒法");
        Log.d("Test", "杨过使用打狗棒法");
    }
    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }
}