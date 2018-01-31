package internet.yxd.receiver.case2_activity_send;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/*
以Service作为广播的接收方
 */
public class Case2Service extends Service {
    public Case2Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    static class LocalReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra("activity");
            Log.d("Test", "服务端收到了前端的信息："+text);
        }
    }
}
