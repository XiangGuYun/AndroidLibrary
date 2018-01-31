package yxd.pool.case2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import yxd.pool.R;

public class SingleActivity extends AppCompatActivity implements Runnable{

    private ExecutorService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        service = Executors.newSingleThreadExecutor();
    }

    public void request(View v){
        service.execute(this);
    }

    @Override
    public void run() {
        Log.d("Test", Thread.currentThread().getName()+"正在处理用户的请求");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
