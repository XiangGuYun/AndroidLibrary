package internet.yxd.receiver.case1_service_send;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
/*
将服务作为广播发送方
 */
public class Case1Service extends Service {

    private IntentFilter intentFilter;
    private LocalBroadcastManager manager;
    private MainActivity.LocalReceiver localReceiver;

    public Case1Service() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播接收器
        register();
        //向广播接收器发送广播
        send();
    }

    private void send() {
        Intent intent = new Intent("action");
        intent.putExtra("service", "这是来自服务端的信息");
        //发送本地广播
        manager.sendBroadcast(intent);
    }

    private void register() {
        //本地广播管理器
        manager = LocalBroadcastManager.getInstance(this);
        //意图过滤器
        intentFilter = new IntentFilter();
        //添加过滤Action
        intentFilter.addAction("action");
        //Activity的本地广播接收器
        localReceiver = new MainActivity.LocalReceiver();
        //注册本地接收器
        manager.registerReceiver(localReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注册
        manager.unregisterReceiver(localReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
