package yxd.asynctask.case2_download_img;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import yxd.asynctask.R;

public class Case2Activity extends AppCompatActivity {

    private static final String URL = "https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png";
    private static ImageView imageView;
    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        imageView = findViewById(R.id.iv);
        myTask = new MyTask();

    }

    public void download(View view) {
       myTask.execute(URL);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    static class MyTask extends AsyncTask<String, Void, Bitmap>{

        private static Bitmap bitmap;

        @Override
        protected Bitmap doInBackground(String... strings) {
            HttpURLConnection conn = null;
            try {
                // 利用string url构建URL对象
                URL mURL = new URL(strings[0]);
                conn = (HttpURLConnection) mURL.openConnection();
                conn.setRequestMethod("GET");
                conn.setReadTimeout(5000);
                conn.setConnectTimeout(10000);
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    return bitmap;
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

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onDestroy() {
        myTask.cancel(true);
        imageView = null;
        MyTask.bitmap.recycle();
        super.onDestroy();
    }
}
