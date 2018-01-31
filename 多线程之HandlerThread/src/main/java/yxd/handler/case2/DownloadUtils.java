package yxd.handler.case2;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by asus on 2018/1/8.
 */

public class DownloadUtils {

    public static Bitmap downloadBitmap(String url){
        HttpURLConnection conn = null;
        try {
            // 利用string url构建URL对象
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                return BitmapFactory.decodeStream(is);
            } else {
                Log.d("Test", "异常1");
                throw new NetworkErrorException("response status is "+responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Test", "异常2");
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

}
