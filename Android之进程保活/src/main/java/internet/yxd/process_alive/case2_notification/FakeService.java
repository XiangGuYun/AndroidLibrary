package internet.yxd.process_alive.case2_notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import internet.yxd.process_alive.R;
/*
先用一个临时的Service来绑定某一个Notification，
然后利用相同的id绑定工作的Service，接着关闭临时的Service，
此时Notification会随着FakeService一起关闭，
但工作的Service仍依然处于前台运行状态，进程等级就得到了相应的提升。
 */
public class FakeService extends Service {

    public static FakeService instance = null;

    public FakeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;//保存实例
        //开启服务前运行
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("fake")
                .setContentText("I am fake")
                .setWhen(System.currentTimeMillis());
        startForeground(1, builder.build());
        //真正工作的Service
        Intent intent = new Intent(this, WorkService.class);
        startService(intent);
    }

    @Override
    public void onDestroy() {
        instance=null;
        stopForeground(true);
        super.onDestroy();
    }
}
