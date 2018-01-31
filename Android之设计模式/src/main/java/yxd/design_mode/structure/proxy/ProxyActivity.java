package yxd.design_mode.structure.proxy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Proxy;

import yxd.design_mode.R;
import yxd.design_mode.structure.proxy.proxy.DynamicPurchasing;
import yxd.design_mode.structure.proxy.proxy.Purchasing;
import yxd.design_mode.structure.proxy.subject.IShop;
import yxd.design_mode.structure.proxy.subject.LiuWangShu;
/*
代理模式类型

代理模式的类型主要有以下几点：

远程代理：为一个对象在不同的地址空间提供局部代表，这样系统可以将Server部分的事项隐藏。
虚拟代理：使用一个代理对象表示一个十分耗资源的对象并在真正需要时才创建。
安全代理：用来控制真实对象访问时的权限。
智能指引：当调用真实的对象时，代理处理另外一些事，比如计算真实对象的引用计数，当该对象没有引用时，可以自动释放它；或者访问一个实际对象时，检查是否已经能够锁定它，以确保其他对象不能改变它。
代理模式使用场景

无法或者不想直接访问某个对象时可以通过一个代理对象来间接的访问。
 */
public class ProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy);

        staticProxy();//静态代理

        dynamicProxy();//动态代理

    }

    private void dynamicProxy() {
        IShop liuwangshu=new LiuWangShu();
        //创建动态代理
        DynamicPurchasing mDynamicPurchasing=new DynamicPurchasing(liuwangshu);
        //创建LiuWangShu的ClassLoader
        ClassLoader loader=liuwangshu.getClass().getClassLoader();
        //动态创建代理类
        IShop purchasing= (IShop) Proxy.newProxyInstance(loader,new Class[]{IShop.class},mDynamicPurchasing);
        purchasing.buy();
    }

    private void staticProxy() {
        //创建LiuWangShu
        IShop liuwangshu=new LiuWangShu();
        //创建代购者并将LiuWangShu作为构造函数传
        IShop purchasing=new Purchasing(liuwangshu);
        purchasing.buy();
    }


}
