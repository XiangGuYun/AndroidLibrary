package yxd.design_mode.structure.decorate.decorator;

import android.util.Log;

import yxd.design_mode.structure.decorate.component.Swordsman;

/**
 * Created by asus on 2017/12/17.
 */

public class OuYangFeng extends Master {
    public OuYangFeng(Swordsman mSwordsman) {
        super(mSwordsman);
    }
    public void teachAttackMagic(){
        Log.d("Test", "欧阳锋教授蛤蟆功");
        Log.d("Test", "杨过使用蛤蟆功");
    }
    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }
}
