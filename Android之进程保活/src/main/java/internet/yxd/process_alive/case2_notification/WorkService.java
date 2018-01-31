package internet.yxd.process_alive.case2_notification;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import internet.yxd.process_alive.R;

public class WorkService extends Service {
    public WorkService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // 开启服务前台运行，id与FakeService相同均为1
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("fake")
                .setContentText("I am fake")
                .setWhen(System.currentTimeMillis());
        startForeground(1, builder.build());
        // 关闭FakeService，关闭Notification
        FakeService.instance.stopSelf();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
