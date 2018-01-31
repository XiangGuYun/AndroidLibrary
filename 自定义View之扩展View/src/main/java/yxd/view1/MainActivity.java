package yxd.view1;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.view1.pregressbar.HorizontalProgressBar;

public class MainActivity extends AppCompatActivity {

    HorizontalProgressBar pb1, pb2, pb3, pb4;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    pb1.setProgress(msg.arg1);
                    pb4.setProgress(msg.arg1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.pb1);
        pb2 = findViewById(R.id.pb2);
        pb3 = findViewById(R.id.pb3);
        pb4 = findViewById(R.id.pb4);
        pb1.setProgress(0);
        pb4.setProgress(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 101; i++) {
                   Message message = Message.obtain();
                   message.arg1 = i;
                   message.what = 0x123;
                   handler.sendMessage(message);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
