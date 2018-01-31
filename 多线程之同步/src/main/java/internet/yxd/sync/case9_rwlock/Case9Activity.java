package internet.yxd.sync.case9_rwlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

import internet.yxd.sync.R;

public class Case9Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case9);

        //sync();//使用synchronized实现读读互斥，写写互斥，读写互斥
        lock();//使用读写锁实现读读不互斥，写写互斥，读写互斥

    }

    private void lock() {
        final LockData data = new LockData();

        /*
        创建3个子线程负责写入数据
         */
        Thread writeThread;
        for (int i = 0; i < 3; i++) {
            writeThread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            });
            writeThread.setName("写入线程"+(i+1));
            writeThread.start();
        }

        /*
        创建3个子线程负责读取数据
         */
        Thread readThread;
        for (int i = 0; i < 3; i++) {
            readThread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            });
            readThread.setName("读取线程"+(i+1));
            readThread.start();
        }
    }

    private void sync() {
        final Data data = new Data();

        /*
        创建3个子线程负责写入数据
         */
        Thread writeThread;
        for (int i = 0; i < 3; i++) {
            writeThread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            });
            writeThread.setName("写入线程"+(i+1));
            writeThread.start();
        }

        /*
        创建3个子线程负责读取数据
         */
        Thread readThread;
        for (int i = 0; i < 3; i++) {
            readThread = new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            });
            readThread.setName("读取线程"+(i+1));
            readThread.start();
        }
    }
}
