package internet.yxd.eventbus.case1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import internet.yxd.eventbus.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void back(View view) {
        //向MainActivity发送消息
        EventBus.getDefault().post(new MyEvent("SecondActivity 被点击了"));
        finish();
    }
}
