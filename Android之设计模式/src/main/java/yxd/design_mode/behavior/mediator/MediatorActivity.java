package yxd.design_mode.behavior.mediator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
import yxd.design_mode.behavior.mediator.colleague.Emei;
import yxd.design_mode.behavior.mediator.colleague.Shaolin;
import yxd.design_mode.behavior.mediator.colleague.Wudang;
import yxd.design_mode.behavior.mediator.mediator.Champions;

/*
中介者模式定义

定义：用一个中介者对象来封装一系列的对象交互。中介者使得各对象不需要显式地相互引用，从而使其松散耦合，
而且可以独立地改变它们之间的交互。

Mediator：抽象中介者角色，定义了同事对象到中介者对象的接口。
ConcreteMediator：具体中介者角色，它从具体的同事对象接收消息，向具体同事发出命令。
Colleague：抽象同事角色，定义了中介者对象接口，它只知道中介者而不知道其他同事对象。
ConcreteColleague：具体同事角色，每个具体同事类都知道自己在小范围内的行为，而不知道它在大范围内的目的。

中介者模式简单实现

中介者模式可以拿武侠来举例，江湖中门派众多，门派之前因为想法不同会有很多的利益冲突，这样就会带来无休止的纷争。
为了江湖的安宁，大家推举出了一个大家都认可的武林盟主来对江湖纷争进行调停。
前段时间武当派和峨眉派的的弟子被大力金刚指所杀，大力金刚指是少林派的绝学，因为事情重大，而且少林派实力强大，
武当派和峨眉派不能够直接去少林派去问罪，只能上报武林盟主由武林盟主出面进行调停

优点
符合迪米特原则，将原有的一对多的依赖变成了一对一的依赖，降低类间的耦合。

缺点
中介者会变得庞大且复杂，原本多个对象直接的相互依赖变成了中介者和多个同事类的依赖关系，同事类越多，中介者的逻辑就越复杂。

使用场景
中介者模式很容易实现呢，但是也容易误用，不要着急使用，先要思考你的设计是否合理。
当对象之间的交互变多时，为了防止一个类会涉及修改其他类的行为，可以使用中介者模式，将系统从网状结构变为以中介者为中心的星型结构。

3.代理模式、外观模式和中介者模式对比

当我们学完中介者模式是不是会觉得和此前讲过的代理模式和外观模式有些类似呢？现在我们一一来将它们进行对比。

3.1 代理模式和中介者模式

代理模式是结构型设计模式，它有很多种类型，主要是在访问对象时引入一定程度的间接性，由于有间接性，就可以附加多种的用途，比如进行权限控制。
中介者模式则是为了减少对象之间的相互耦合。虽然网上有很多代理模式和中介者模式的对比，但是在我看来这两者实际上并没有可比性，只是看起来有些类似罢了。

3.2 外观模式和中介者模式

外观模式主要是以封装和隔离为主要任务，中介者则是调停同事类之间的关系，因此，中介者具有部分业务的逻辑控制。他们之间的主要区别为：

外观模式的子系统如果脱离外观模式还是可以运行的，而中介者模式增加了业务逻辑，同事类不能脱离中介者而独自存在。
外观模式中，子系统是不知道外观类的存在的，而中介者模式中，每个同事类都知道中介者。
外观模式将子系统的逻辑隐藏，用户不知道子系统的存在，而中介者模式中，用户知道同事类的存在。
 */
public class MediatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediator);

        Champions champions=new Champions();

        Wudang wudang=new Wudang(champions);//申请加盟
        Shaolin shaolin=new Shaolin(champions);//申请加盟
        Emei emei=new Emei(champions);//申请加盟

        champions.setWudang(wudang);
        champions.setShaolin(shaolin);
        champions.setEmei(emei);

        wudang.sendAlliance("武当弟子被少林大力金刚指所杀");
        emei.sendAlliance("峨眉弟子被少林大力金刚指所杀");
        shaolin.sendAlliance("少林弟子绝不会做出这种事情");
        /*
        输出结果为：
        少林收到消息：武当弟子被少林大力金刚指所杀
        少林收到消息：峨眉弟子被少林大力金刚指所杀
        武当收到消息：少林弟子绝不会做出这种事情
        峨眉收到消息：少林弟子绝不会做出这种事情
         */

    }
}