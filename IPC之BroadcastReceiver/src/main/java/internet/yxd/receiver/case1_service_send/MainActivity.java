package internet.yxd.receiver.case1_service_send;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import internet.yxd.receiver.R;

public class MainActivity extends AppCompatActivity {


    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new Intent(this, Case1Service.class);
        startService(service);
    }

    /*
    自定义本地广播接收器
    用来接收Service发送过来的广播
    这个接收器需要使用本地管理器进行注册
     */
    static class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("service");
            Log.d("Test","前端收到了广播: "+text);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(service);
    }
}
