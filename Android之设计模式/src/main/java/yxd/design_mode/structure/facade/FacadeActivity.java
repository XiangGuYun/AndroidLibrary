package yxd.design_mode.structure.facade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
import yxd.design_mode.structure.facade.facade.ZhangWuJi;
/*
外观模式介绍

当我们开发Android的时候，无论是做SDK还是封装API，我们大多都会用到外观模式，
它通过一个外观类使得整个系统的结构只有一个统一的高层接口，这样能降低用户的使用成本。

外观模式定义

为系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使得子系统更加容易使用。

外观模式结构图

Facade：外观类，知道哪些子系统类负责处理请求，将客户端的请求代理给适当的子系统对象。
Subsystem：子系统类，实现子系统的功能，处理外观类指派的任务，注意子系统类不含有外观类的引用。

外观模式使用场景

构建一个有层次结构的子系统时，使用外观模式定义子系统中每层的入口点，如果子系统之间是相互依赖的，
则可以让他们通过外观接口进行通信，减少子系统之间的依赖关系。
子系统往往会因为不断的重构演化而变得越来越复杂，大多数的模式使用时也会产生很多很小的类，
这给外部调用他们的用户程序带来了使用的困难，我们可以使用外观类提供一个简单的接口，对外隐藏子系统的具体实现并隔离变化。
当维护一个遗留的大型系统时，可能这个系统已经非常难以维护和拓展，但因为它含有重要的功能，新的需求必须依赖于它，
则可以使用外观类，来为设计粗糙或者复杂的遗留代码提供一个简单的接口，让新系统和外观类交互，而外观类负责与遗留的代码进行交互。
 */
public class FacadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facade);

        ZhangWuJi zhangWuJi=new ZhangWuJi();
        //张无忌使用乾坤大挪移
        zhangWuJi.Qiankun();
        //张无忌使用七伤拳
        zhangWuJi.QiShang();

    }
}
