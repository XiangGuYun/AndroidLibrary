package yxd.webview;

import android.os.Environment;
import android.os.HandlerThread;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by asus on 2017/12/14.
 */

public class HttpThread extends Thread {

    private String mURL;

    public HttpThread(String url){
        Log.d("Test", "初始化了下载线程 "+url);
        mURL = url;
    }

    @Override
    public void run() {
        URL url = null;
        try {
            url = new URL(mURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("Test", e.getMessage());
        }
        HttpURLConnection conn = null;
        File file = null;
        InputStream is=null;
        OutputStream os=null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            file = new File(Environment.getExternalStorageDirectory(), "test.apk");
            os = new FileOutputStream(file);
            byte[] bytes = new byte[6*1024];
            int len;
            is = conn.getInputStream();
            while ((len=is.read(bytes))!=-1){
                os.write(bytes, 0, len);
                Log.d("Test", "正在下载...");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Test", e.getMessage());
        } finally {
            Log.d("Test", "结束了");
            try {
                if(is != null)
                    is.close();
                if(os != null)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
