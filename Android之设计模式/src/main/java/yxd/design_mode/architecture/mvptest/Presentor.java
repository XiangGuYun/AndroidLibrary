package yxd.design_mode.architecture.mvptest;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import yxd.design_mode.MyApplication;

/**
 * Created by asus on 2017/12/25.
 */

public class Presentor {

    private IRequest iRequest;

    public Presentor(IRequest iRequest){
        this.iRequest = iRequest;
    }

    public void query(){
        StringRequest request = new StringRequest(Request.Method.GET,
                Constants.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        WeatherWrapper wrapper = gson.fromJson(s, WeatherWrapper.class);
                        iRequest.setText(Constants.WEATHER_DATE, wrapper.getSk_info().getDate());
                        iRequest.setText(Constants.WEATHER_CITY, wrapper.getSk_info().getCityName());
                        iRequest.setText(Constants.WEATHER_TEMP, wrapper.getSk_info().getTemp());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {}
        });
        request.setTag("abcGet");
        MyApplication.getQueue().add(request);
    }
}
