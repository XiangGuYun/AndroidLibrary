package yxd.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by asus on 2017/12/18.
 */

public class VolleyRequest {

    public static StringRequest stringRequest;
    public static Context context;

    public static void requestGet(Context ctx, String url, String tag, Response.Listener<String> listener,
                                  Response.ErrorListener errorListener){
        MyApplication.getQueue().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET,
                url,
                listener,
                errorListener
        );
        stringRequest.setTag(tag);
        MyApplication.getQueue().add(stringRequest);
        MyApplication.getQueue().start();
    }

    public static void requestPost(Context ctx, String url, String tag, Response.Listener<String> listener,
                                          Response.ErrorListener errorListener, final Map<String, String> map){
        MyApplication.getQueue().cancelAll(tag);
        stringRequest = new StringRequest(
                url,listener,errorListener
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        MyApplication.getQueue().add(stringRequest);
        MyApplication.getQueue().start();

    }


}
