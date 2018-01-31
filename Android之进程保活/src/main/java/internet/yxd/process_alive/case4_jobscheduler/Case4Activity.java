package internet.yxd.process_alive.case4_jobscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.process_alive.R;
/*
JobService只适用于Android5.0以上的系统；
其次，当进程被force-stop指令杀死后，JobService依旧无法拉活进程。
 */
public class Case4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case4);
        startService(new Intent(this, MyJobService.class));
    }
}
