package yxd.pool.case2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import yxd.pool.R;

public class ScheduledActivity extends AppCompatActivity implements Runnable{

    private ScheduledExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        service = Executors.newScheduledThreadPool(3);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.shutdownNow();
            }
        });
    }

    public void request(View v){
        service.scheduleWithFixedDelay(this,3,1, TimeUnit.SECONDS);
        v.setClickable(false);
        Log.d("Test", "3秒后每隔1秒执行一次任务");
    }

    @Override
    public void run() {
        Log.d("Test", Thread.currentThread().getName()+"正在处理用户的请求");
    }
}
