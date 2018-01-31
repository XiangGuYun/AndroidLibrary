package yxd.recyclerview.case1_base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yxd.recyclerview.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void btn1(View view) {
        startActivity(new Intent(this, ListActivity.class));
    }

    public void btn2(View view) {
        startActivity(new Intent(this, GridActivity.class));
    }

    public void btn3(View view) {
        startActivity(new Intent(this, WaterFallActivity.class));
    }
}
