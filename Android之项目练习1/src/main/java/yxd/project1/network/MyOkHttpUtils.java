package yxd.project1.network;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import yxd.project1.constant.Constant;
import yxd.project1.context.MyApplication;
import yxd.project1.utils.StringUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asus on 2018/1/1.
 */

public class MyOkHttpUtils {

    public static void post(String url, Map<String, String> map, Result result){
        FormBody.Builder builder = new FormBody.Builder();
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            builder.add((String) entry.getKey(), (String) entry.getValue());
        }
        MyApplication.getOkHttpClient().newCall(new Request.Builder()
                .url(url).post(builder.build()).build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                result.failure(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result.success(response.body().string());
            }
        });
    }

   public static void get(String url, Result result){
        Request.Builder requestBuilder = new Request.Builder().url(url);
        Request request = requestBuilder.build();
        Call call= MyApplication.getOkHttpClient().newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                result.failure(e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                result.success(response.body().string());
            }
        });
    }

    public static void get1(FragmentActivity ctx, String url, Success result1, Failure result2){
        MyApplication.getOkHttpClient().newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                result2.failure(e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                result1.success(response.body().string());
            }
        });
    }

   public interface Result{
       void success(String response);
       void failure(IOException e);
   }

   public interface Success{
       void success(String response);
   }

   public interface Failure{
        void failure(IOException e);
    }

}
