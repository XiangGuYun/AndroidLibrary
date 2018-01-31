package yxd.service;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import yxd.service.bean.FileInfo;

/*
网络下载关键点
获取网络文件的长度
在本地设置一个文件，设置其长度
从数据库中获取上次下载的进度
从上次下载的位置下载数据，同时保存进度到数据库
将下载进度回传给Activity
 */
public class MainActivity extends AppCompatActivity {

    private static final int FILE_LENGTH = 0;
    private static final int PROCESS = 0;
    public static final String FILE_URL = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
    public static final String FILE_NAME = "ogo_white_fe6da1ec.png";
    TextView tv_filename;
    ProgressBar pb;
    private FileInfo fileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_filename = findViewById(R.id.tv_filename);
        pb = findViewById(R.id.pb);
        pb.setMax(100);
        //创建文件信息对象
        fileInfo = new FileInfo(1, FILE_URL, FILE_NAME, FILE_LENGTH, PROCESS);
        /*
        申请写入权限
         */
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        //注册广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadService.ACTION_UPDATE);
        registerReceiver(receiver, filter);
    }

    /*
    下载按钮
     */
    public void download(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.setAction(DownloadService.ACTION_START);
        intent.putExtra(DownloadService.FILE_INFO, fileInfo);
        startService(intent);
    }

    /*
    暂停按钮
     */
    public void pause(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.setAction(DownloadService.ACTION_STOP);
        intent.putExtra(DownloadService.FILE_INFO, fileInfo);
        startService(intent);
    }

    /*
    更新UI的广播接收器
     */
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction()==DownloadService.ACTION_UPDATE){
                int process = intent.getIntExtra("process", 0);
                pb.setProgress(process);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
