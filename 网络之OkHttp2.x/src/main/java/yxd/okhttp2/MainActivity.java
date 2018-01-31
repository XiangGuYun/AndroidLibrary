package yxd.okhttp2;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final String APPKEY = "JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";
    private TextView tv_result;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = findViewById(R.id.tv_result);
    }

    /*
    GET基本操作
     */
    public void doGet(View view) {
        client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url("http://www.imooc.com").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                L.e(e.getMessage());
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                L.e(response.body().string());
            }
        });
//        try {
//            call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void doPost(View view) {
        OkHttpClient client = new OkHttpClient();
        FormEncodingBuilder rbBuilder = new FormEncodingBuilder();
        RequestBody requestBody = rbBuilder
                .add("kw", "%E7%99%BD")
                .add("site", "qq.com")
                .add("apikey", APPKEY)
                .build();
        Request.Builder builder = new Request.Builder();
        final Request request = builder.url(Constant.BASE_URL).post(requestBody).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("Test", "请求失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("Test", "请求成功!"+response.body().string());
            }
        });


    }

    /*
    提交JSON字符串
     */
    public void doPostString(View view) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"),
                "{username:hyman,password:123}");
        Request.Builder builder = new Request.Builder();
        Request request  = builder.url("").post(requestBody).build();
    }

    /*
    提交文件
     */
    public void doPostFile(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        if(!file.exists()){
            L.e("文件不存在");
            return;
        }
        //mime type
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),
                file);
        Request.Builder builder = new Request.Builder();
        Request request =  builder.url("").post(requestBody).build();

    }

    /*
    上传文件
     */
    public void doUpload(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        if(!file.exists()){
            L.e("文件不存在");
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),
                file);

        MultipartBuilder multipartBuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        multipartBuilder.addFormDataPart("username", "hyman");
        multipartBuilder.addFormDataPart("password", "123");
        RequestBody body = multipartBuilder.addFormDataPart("mPhoto", "hyman.jpg", requestBody).build();

        //mime type

        Request.Builder builder = new Request.Builder();
        Request request =  builder.url("").post(body).build();
    }

    /*
    下载文件
     */
    public void doDownUpload(View view) {
           Request.Builder builder = new Request.Builder();
           final Request request = builder
                   .get()
                   .url("filePath")
                   .build();
           Call call = client.newCall(request);
           call.enqueue(new Callback() {
               @Override
               public void onFailure(Request request, IOException e) {

               }

               @Override
               public void onResponse(Response response) throws IOException {
                   InputStream stream = response.body().byteStream();
                   int len = 0;
                   File file = new File(Environment.getExternalStorageDirectory(),
                           "hyman.jpg");
                   byte[] buf = new byte[128];
                   FileOutputStream fos = new FileOutputStream(file);
                   while ((len=stream.read(buf))!=-1){
                        fos.write(buf, 0, len);
                   }
                   fos.flush();
                   fos.close();
                   stream.close();
               }
           });

    }
}
