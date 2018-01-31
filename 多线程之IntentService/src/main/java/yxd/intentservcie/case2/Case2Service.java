package yxd.intentservcie.case2;

import android.accounts.NetworkErrorException;
import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Case2Service extends IntentService {


    public Case2Service() {
        //在构造函数中为handlerthread命名
        super("Case2Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            HttpURLConnection conn = null;
            //获取Activity传来的图片URL
            String url = intent.getStringExtra("url");
            try {
                // 利用string url构建URL对象
                URL mURL = new URL(url);
                conn = (HttpURLConnection) mURL.openConnection();//打开连接
                conn.setRequestMethod("GET");//get请求
                conn.setReadTimeout(5000);//读取超时
                conn.setConnectTimeout(10000);//连接超时
                //连接网络下载并获取返回值
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = conn.getInputStream();
                    //利用EventBus将Bitmap发送到Case2Activity
                    EventBus.getDefault().post(BitmapFactory.decodeStream(is));
                } else {
                    Log.d("Test", "异常1");
                    throw new NetworkErrorException("response status is "+responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Test", "异常2");
            } finally {
                if (conn != null) {
                    conn.disconnect();//关闭连接
                }
            }
        }
    }
}
