package yxd.socket.case2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Case2Service extends Service {
    public Case2Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
