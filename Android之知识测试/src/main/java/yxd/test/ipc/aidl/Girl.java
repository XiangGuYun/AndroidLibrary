package yxd.test.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Girl extends Service {
    public Girl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
