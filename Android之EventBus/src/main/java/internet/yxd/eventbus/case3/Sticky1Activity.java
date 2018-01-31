package internet.yxd.eventbus.case3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import internet.yxd.eventbus.R;
/*
粘性服务
允许后注册

例如一名读者在订阅了《XX早报》后依然能收到报社之前发行的《XX早报》

 */
public class Sticky1Activity extends AppCompatActivity {

    TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky1);
        tv_test=findViewById(R.id.tv_test);
    }

    /*
    方法名随意取
    但是注解中一定要添加sticky = true
    POSTING表示事件发布线程与接收处理线程相一致
     */
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent){
        tv_test.setText(messageEvent.getMessage());
    }

    /*
    暂且不注册EventBus，跳转第二个Activity
     */
    public void go(View view) {
        startActivity(new Intent(this, Sticky2Activity.class));
    }

    /*
    跳转回来再点击注册，发现文本上更新显示了“粘性事件”
     */
    public void register(View view) {
        EventBus.getDefault().register(this);
    }

}
