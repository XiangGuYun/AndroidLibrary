package yxd.design_mode.architecture.mvptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import yxd.design_mode.MyApplication;
import yxd.design_mode.R;

public class MVPActivity extends AppCompatActivity implements IRequest{


    private Presentor presentor;
    private TextView tv_date, tv_city, tv_temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        tv_date = findViewById(R.id.tv_date);
        tv_city = findViewById(R.id.tv_city);
        tv_temp = findViewById(R.id.tv_temp);
        presentor = new Presentor(this);
    }

    public void query(View view) {
       presentor.query();
    }


    @Override
    public void setText(int flag, String text) {
        switch (flag){
            case Constants.WEATHER_DATE:
                tv_date.setText(text);
                break;
            case Constants.WEATHER_CITY:
                tv_city.setText(text);
                break;
            case Constants.WEATHER_TEMP:
                tv_temp.setText(text);
                break;
        }
    }
}
