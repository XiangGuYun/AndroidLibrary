package yxd.scroll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view) {
        startActivity(new Intent(this, LayoutActivity.class));
    }

    public void btn2(View view) {
        startActivity(new Intent(this, OffsetActivity.class));
    }

    public void btn3(View view) {
        startActivity(new Intent(this, ParamsActivity.class));
    }

    public void btn4(View view) {
        startActivity(new Intent(this, ScrollToByActivity.class));
    }

    public void btn5(View view) {
        startActivity(new Intent(this, AnimatorActivity.class));
    }
}
