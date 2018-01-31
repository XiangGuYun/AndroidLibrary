package internet.yxd.eventbus.case3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import internet.yxd.eventbus.R;

public class Sticky2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticky2);
    }

    public void sendCommonEvent(View view) {
        EventBus.getDefault().post(new MessageEvent("普通事件"));
        finish();
    }

    public void sendStickyEvent(View view) {
        //EventBus通过postSticky来发送黏性事件
        EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
        finish();
    }
}
