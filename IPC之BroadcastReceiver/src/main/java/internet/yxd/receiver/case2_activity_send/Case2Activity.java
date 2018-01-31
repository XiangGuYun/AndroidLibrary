package internet.yxd.receiver.case2_activity_send;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import internet.yxd.receiver.R;
/*
以Activity作为广播的发送方
 */
public class Case2Activity extends AppCompatActivity {

    private LocalBroadcastManager manager;
    private IntentFilter intentFilter;
    private Case2Service.LocalReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);

        manager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter("action");
        receiver = new Case2Service.LocalReceiver();
        manager.registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(receiver);
    }

    public void send(View view) {
        Intent intent = new Intent("action");
        intent.putExtra("activity", "这是来自前端的消息");
        manager.sendBroadcast(intent);
    }
}
