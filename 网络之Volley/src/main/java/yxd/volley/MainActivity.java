package yxd.volley;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getQueue().cancelAll("abcGet");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void volley_Post2() {
        Map<String, String> map = new ArrayMap<>();
        map.put("phone", "13916434237");
        map.put("phone", "13916434238");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "",
                new JSONObject(map),
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
        });
        request.setTag("abcGet");
        MyApplication.getQueue().add(request);
    }

    private void volley_Post1() {
        StringRequest request = new StringRequest(Request.Method.POST, "",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                    }
                 },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                }
        }){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new ArrayMap<>();
                map.put("phone", "13916434237");
                map.put("phone", "13916434238");
                return map;
            }
        };
        request.setTag("abcGet");
        MyApplication.getQueue().add(request);
    }

    private void volley_Get2() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "",
                null,//携带的参数体
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("abcGet");
        MyApplication.getQueue().add(request);
    }

    private void volley_Get1() {
        StringRequest request = new StringRequest(Request.Method.GET,
                "",
            new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("abcGet");
        MyApplication.getQueue().add(request);


    }

    public void get(View view) {
        VolleyRequest.requestGet(getApplicationContext(), "http://www.baidu.com", "abc", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("Test", s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }
}
