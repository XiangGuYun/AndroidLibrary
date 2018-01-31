package yxd.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import yxd.service.bean.FileInfo;

public class DownloadService extends Service {
    public static final String ACTION_START = "as1";
    public static final String FILE_INFO = "fileinfo";
    public static final String TEST = "Test";
    public static final String ACTION_STOP = "as2";
    public static final String ACTION_UPDATE = "update";
    private static final int MSG_INIT = 0x123;
    public static final String DOWMLOAD_PATH = Environment.getExternalStorageDirectory()+"/downloads/";
    private DownloadTask task = null;

    public DownloadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //获得Activity传来的参数
        if(ACTION_START.equals(intent.getAction())){
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(FILE_INFO);
            Log.d(TEST, "start "+fileInfo.toString());
            new InitThread(fileInfo).start();
//            OkHttpUtils//
//                    .get()//
//                    .url(MainActivity.FILE_URL)//
//                    .build()//
//                    .execute(new FileCallBack(DOWMLOAD_PATH, MainActivity.FILE_NAME)//
//                    {
//                        @Override
//                        public void onError(Call call, Exception e, int id) {
//                            Log.d("Test", "下载失败");
//                        }
//
//                        @Override
//                        public void onResponse(File response, int id) {
//                            Log.d("Test", "下载成功");
//                        }
//                    });
        }else if(ACTION_STOP.equals(intent.getAction())){
            FileInfo fileInfo = (FileInfo) intent.getSerializableExtra(FILE_INFO);
            Log.d(TEST, "stop "+fileInfo.toString());
            if(task != null){
                task.isPause = true;
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_INIT:
                    FileInfo fileInfo = (FileInfo) msg.obj;
                    //Log.d("Test", fileInfo.toString());
                    task = new DownloadTask(DownloadService.this, fileInfo);
                    task.download();
                    break;
            }
        }
    };

    public class InitThread extends Thread{
        private FileInfo fileInfo;

        public InitThread(FileInfo info){
            Log.d("Test", "构造 "+info.getName());
            fileInfo = info;
        }

        @Override
        public void run() {
            HttpURLConnection conn = null;
            RandomAccessFile raf = null;
            Log.d("Test", "line 1");
            try{
                URL url = new URL(fileInfo.getUrl());
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(3000);
                conn.setRequestMethod("GET");
                conn.connect();
                int length = -1;
                Log.d("Test", "line 1.1 "+conn.getResponseCode());
                if(conn.getResponseCode()== HttpURLConnection.HTTP_OK){
                    //获取文件长度
                    length = conn.getContentLength();
                    Log.d("Test", "文件长度是"+length);
                }
                if(length<=0){
                    Log.d("Test", "line 2");
                    return;
                }
                //下载路径
                File dir = new File(DOWMLOAD_PATH);
                if(!dir.exists()){
                    dir.mkdir();
                }
                //下载文件
                File file = new File(dir, fileInfo.getName());
                raf = new RandomAccessFile(file, "rwd");//rwd:读取写入删除权限
                //设置文件长度
                raf.setLength(length);
                fileInfo.setLength(length);
                handler.obtainMessage(MSG_INIT, fileInfo).sendToTarget();
                Log.d("Test", "line 3");
            }catch (Exception e){
                Log.d("Test", "异常 "+e.getMessage());
            }finally {
                if(conn != null) conn.disconnect();
                if(raf != null) try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
