package yxd.design_mode.structure.decorate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
import yxd.design_mode.structure.decorate.component.YangGuo;
import yxd.design_mode.structure.decorate.decorator.HongQiGong;
import yxd.design_mode.structure.decorate.decorator.OuYangFeng;
/*
优点

通过组合而非继承的方式，动态的来扩展一个对象的功能，在运行时选择不同的装饰器，从而实现不同的行为。
有效避免了使用继承的方式扩展对象功能而带来的灵活性差，子类无限制扩张的问题。
具体组件类与具体装饰类可以独立变化，用户可以根据需要增加新的具体组件类和具体装饰类，在使用时再对其进行组合，原有代码无须改变，符合“开闭原则”。

缺点

装饰链不能过长，否则会影响效率。
因为所有对象都是继承于Component,所以如果Component内部结构发生改变，则不可避免地影响所有子类(装饰者和被装饰者)，如果基类改变，势必影响对象的内部。
比继承更加灵活机动的特性，也同时意味着装饰模式比继承更加易于出错，排错也很困难，对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为烦琐，
所以只在必要的时候使用装饰者模式。

使用场景

在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。
需要动态地给一个对象增加功能，这些功能可以动态的撤销。
当不能采用继承的方式对系统进行扩充或者采用继承不利于系统扩展和维护时。

装饰模式和代理模式

在上一篇文章设计模式之代理模式中我们讲到了代理模式，你会觉得代理模式和装饰模式有点像，都是持有了被代理或者被装饰对象的引用。
它们两个最大的不同就是装饰模式对引用的对象增加了功能，而代理模式只是对引用对象进行了控制却没有对引用对象本身增加功能。
 */
public class DecorateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorate);

        //创建杨过
        YangGuo mYangGuo=new YangGuo();
        //洪七公教授杨过打狗棒法，杨过会了打狗棒法
        HongQiGong mHongQiGong=new HongQiGong(mYangGuo);
        mHongQiGong.attackMagic();
        //欧阳锋教授杨过蛤蟆功，杨过学会了蛤蟆功
        OuYangFeng mOuYangFeng=new OuYangFeng(mYangGuo);
        mOuYangFeng.attackMagic();

    }
}
