package internet.yxd.process_alive.case1_onepixel_activity;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/*
http://blog.csdn.net/superxlcr/article/details/70244803?ref=myread
 */
public class WorkService extends Service {

    private ScreenBroadcastReceiver receiver;

    public WorkService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        receiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
