package internet.yxd.process_alive.case3_service_revive;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/*
修改返回值的缺陷：（被连杀，被强制停止）
首先，如果短时间内Service被杀死多次，那么我们的系统将不再拉起进程；
其次，如果我们的进程被取得Root权限的管理工具或系统工具通过force-stop指令停止掉时，将无法重启。
 */
public class WorkService extends Service {
    public WorkService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Test","调用了onCreate");
    }
    /*
    onStartCommand返回值介绍
    */
    /*
    START_NOT_STICKY：非粘性启动
    Service所在进程被杀死后将不会被重启。
    */
    /*
    START_REDELIVER_INTENT:重发意图粘性启动
    Service所在进程被杀死后，系统会重启Service，
    并且在调用onStartCommand方法时，会发送最后传送的Intent。
    */
    /*
    START_STICKY:粘性启动
    Service所在进程被杀死后，系统会重启Service，
    在onStartCommand方法中有可能会传输null的Intent参数。
    */
    /*
    START_STICKY_COMPATIBILITY:兼容性粘性启动
    START_STICKY的兼容版本，该参数并不保证onStartCommand方法会被调用。
    */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Test", "work service onStartCommand");
        Log.d("Test", "work service onStartCommand intent :" + intent);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
