package internet.yxd.process_alive.case3_service_revive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.process_alive.R;

public class Case3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        startService(new Intent(this, WorkService.class));
    }
}
