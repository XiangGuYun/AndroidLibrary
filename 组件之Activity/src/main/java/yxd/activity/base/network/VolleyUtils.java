package yxd.activity.base.network;

import android.app.Application;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by asus on 2017/12/21.
 */

public class VolleyUtils {

    public static RequestQueue queue;
    public static StringRequest stringRequest;
    public static Context context;

    public static void init(Application app){
        context = app.getApplicationContext();
        queue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getQueue(){
        return queue;
    }

    public static void requestGet(String url, String tag, Response.Listener<String> listener,
                                  Response.ErrorListener errorListener){
        queue.cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,
                url,
                listener,
                errorListener
        );
        stringRequest.setTag(tag);
        queue.add(stringRequest);
        queue.start();
    }

    public static void requestPost(Context ctx, String url, String tag, Response.Listener<String> listener,
                                   Response.ErrorListener errorListener, final Map<String, String> map){
        queue.cancelAll(tag);
        stringRequest = new StringRequest(
                url,listener,errorListener
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        queue.add(stringRequest);
        queue.start();

    }

}
