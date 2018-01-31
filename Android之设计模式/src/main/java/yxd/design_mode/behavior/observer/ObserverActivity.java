package yxd.design_mode.behavior.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;

/*
使用场景

关联行为场景，需要注意的是，关联行为是可拆分的，而不是“组合”关系。
事件多级触发场景。
跨系统的消息交换场景，如消息队列、事件总线的处理机制。
优点

解除耦合，让耦合的双方都依赖于抽象，从而使得各自的变换都不会影响另一边的变换。

缺点

在应用观察者模式时需要考虑一下开发效率和运行效率的问题，程序中包括一个被观察者、多个观察者，
开发、调试等内容会比较复杂，而且在Java中消息的通知一般是顺序执行，那么一个观察者卡顿，
会影响整体的执行效率，在这种情况下，一般会采用异步实现。

android源码中也有很多使用了观察者模式，比如OnClickListener、ContentObserver、
android.database.Observable等；还有组件通讯库RxJava、RxAndroid、EventBus；
在这里将拿我们最常用的Adapter的notifyDataSetChanged()方法
 */
public class ObserverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        SubscriptionSubject mSubscriptionSubject=new SubscriptionSubject();
        //创建微信用户
        WeiXinUser user1=new WeiXinUser("杨影枫");
        WeiXinUser user2=new WeiXinUser("月眉儿");
        WeiXinUser user3=new WeiXinUser("紫轩");
        //订阅公众号
        mSubscriptionSubject.attach(user1);
        mSubscriptionSubject.attach(user2);
        mSubscriptionSubject.attach(user3);
        //公众号更新发出消息给订阅的微信用户
        mSubscriptionSubject.notify("刘望舒的专栏更新了");
    }
}
