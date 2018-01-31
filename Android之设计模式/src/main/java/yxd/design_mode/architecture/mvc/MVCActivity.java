package yxd.design_mode.architecture.mvc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import yxd.design_mode.R;

public class MVCActivity extends AppCompatActivity {

    private TextView tv_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        tv_top = findViewById(R.id.top_contributor);
    }

    public void get(View v){

    }

    public void change(View v){

    }
}
