package yxd.design_mode.creation.abstract_factory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
import yxd.design_mode.creation.abstract_factory.ab_factory.ComputerFactory;
import yxd.design_mode.creation.abstract_factory.con_factory.HpFactory;
import yxd.design_mode.creation.abstract_factory.con_factory.LenovoFactory;

/*
AbstractFactory：抽象工厂，它声明了用来创建不同产品的方法。
ConcreteFactory：具体工厂，实现抽象工厂中定义的创建产品的方法。
AbstractProduct：抽象产品，为每种产品声明业务方法。比如上图的AbstractProductA和 AbstractProductB。
ConcreteProduct：具体产品，定义具体工厂生产的具体产品，并实现抽象产品中定义的业务方法。

抽象工厂模式的优缺点

优点
具体类的创建实例过程与客户端分离，客户端通过工厂的抽象接口操纵实例，客户端并不知道具体的实现是谁。

缺点
如果增加新的产品族则也需要修改抽象工厂和所有的具体工厂。

抽象工厂模式的使用场景

一个系统不依赖于产品线实例如何被创建、组合和表达的细节。
系统中有多于一个的产品线，而每次只使用其中某一产品线。
一个产品线（或是一组没有任何关系的对象）拥有相同的约束。
 */
public class FactoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory2);

        ComputerFactory lenocoFactory=new LenovoFactory();

        lenocoFactory.createDesktopComputer().start();

        lenocoFactory.createNotebookComputer().start();

        ComputerFactory hpFactory=new HpFactory();

        hpFactory.createDesktopComputer().start();

        hpFactory.createNotebookComputer().start();

    }
}
