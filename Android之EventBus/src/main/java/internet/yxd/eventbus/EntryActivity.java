package internet.yxd.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import internet.yxd.eventbus.case1.MainActivity;
import internet.yxd.eventbus.case2.ThirdActivity;
import internet.yxd.eventbus.case3.Sticky1Activity;

/*
EventBus三要素

Event：事件，可以是任意类型的对象。

Subscriber：事件订阅者，
在EventBus3.0之前消息处理的方法只能限定于onEvent、onEventMainThread、
onEventBackgroundThread和onEventAsync，他们分别代表四种线程模型。
在EventBus3.0之后，事件处理的方法可以随便取名，但是需要添加一个注解@Subscribe，并且要指定线程模型（默认为POSTING）。

Publisher：事件发布者，可以在任意线程任意位置发送事件，直接调用EventBus的post(Object)方法。
可以自己实例化EventBus对象，但一般使用EventBus.getDefault()就好了。
 */
/*
EventBus线程模型

POSTING（默认）：事件处理线程与发布线程一致。

MAIN:事件在主线程中处理。

BACKGROUND：事件在固定的子线程中处理，若发布线程为主线程则新建该   子线程。

ASYNC：事件在新建的子线程中处理。
 */
/*
EventBus基本用法

1.自定义一个事件类
public class MessageEvent {
    ...
}

2.在需要订阅事件的地方注册事件
EventBus.getDefault().register(this);

3.发送事件
EventBus.getDefault().post(messageEvent);

4.处理事件
@Subscribe(threadMode = ThreadMode.MAIN)//线程模型不指定则为POSTING
public void XXX(MessageEvent messageEvent) {
    ...
}

5.取消事件订阅
EventBus.getDefault().unregister(this);//通常在onDestroy方法中调用
 */
/*
EventBus黏性事件
黏性事件：在发送事件之后再订阅该事件也能收到该事件

处理黏性事件：@Subscribe(threadMode = ThreadMode.POSTING，sticky = true)
发送黏性事件：EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
 */
public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    public void case1(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void case2(View view) {
        startActivity(new Intent(this, ThirdActivity.class));
    }

    public void case3(View view) {
        startActivity(new Intent(this, Sticky1Activity.class));
    }
}
