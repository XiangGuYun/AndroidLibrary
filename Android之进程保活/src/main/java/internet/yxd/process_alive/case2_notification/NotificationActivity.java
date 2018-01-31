package internet.yxd.process_alive.case2_notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.process_alive.R;
/*
这种方法也适用于Service在后台提供服务的场景。由于没有Activity的缘故，
我们Service所在进程的oom_adj值通常是较高的，进程等级较低，容易被系统回收内存时清理掉。
这时我们可以通过startForeground方法，把我们的服务提升为前台服务，提高进程的等级。
但提升为前台服务必须绑定一个相应的Notification，这是我们不愿意看到的。
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = new Intent(this, FakeService.class);
        startService(intent);
        finish();

    }
}
