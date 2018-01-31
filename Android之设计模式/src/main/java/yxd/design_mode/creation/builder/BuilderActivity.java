package yxd.design_mode.creation.builder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;

/*
http://blog.csdn.net/jie1991liu/article/details/49640725


建造者模式简介

定义

建造者模式（builder），将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

简介

建造者模式（builder）是创建一个复杂对象的创建型模式，将构建复杂对象的过程和它的部件解耦，使得构建过程和部件的表示分离开来。
例如我们要DIY一个台式机电脑，我们找到DIY商家，我们可以要求这台电脑的cpu或者主板或者其他的部件都是什么牌子的什么配置的，
这些部件是我们可以根据我们的需求来变化的，但是这些部件组装成电脑的过程是一样的，我们不需要知道这些部件是怎样组装成电脑的，
我们只需要提供部件的牌子和配置就可以了。对于这种情况我们就可以采用建造者模式，将部件和组装过程分离，
使得构建过程和部件都可以自由拓展，两者之间的耦合也降到最低。

Dirextor: 指挥者类，用于统一组装流程
Builder：抽象Builder类，规范产品的组建，一般是由子类实现。
ConcreteBulider: 抽象Builder类的实现类，实现抽象Builder类定义的所有方法，并且返回一个组建好的对象
Product: 产品类

使用建造者模式的场景和优缺点

使用场景

当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。
相同的方法，不同的执行顺序，产生不同的事件结果时。
多个部件或零件,都可以装配到一个对象中，但是产生的运行结果又不相同时。
产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能。
创建一些复杂的对象时，这些对象的内部组成构件间的建造顺序是稳定的，但是对象的内部组成构件面临着复杂的变化。
优缺点

优点：

使用建造者模式可以使客户端不必知道产品内部组成的细节。
具体的建造者类之间是相互独立的，容易扩展。
由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。
缺点：

产生多余的Build对象以及Dirextor类。
 */
public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);

        Builder mBuilder=new MoonComputerBuilder();
        Director mDirecror=new Director(mBuilder);
        //组装电脑
        mDirecror.CreateComputer("i7-6700","华擎玩家至尊","三星DDR4");
    }
}
