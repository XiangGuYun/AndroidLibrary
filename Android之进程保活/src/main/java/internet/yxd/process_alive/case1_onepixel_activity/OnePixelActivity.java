package internet.yxd.process_alive.case1_onepixel_activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import internet.yxd.process_alive.R;

public class OnePixelActivity extends AppCompatActivity {

    public static OnePixelActivity instance ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_pixel);
        Window window = getWindow();
        //放在左上角
        window.setGravity(Gravity.TOP|Gravity.LEFT);
        //宽高为1px
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = 1;
        layoutParams.height = 1;
        //坐标
        layoutParams.x = 0;
        layoutParams.y = 0;
        window.setAttributes(layoutParams);
        instance = this;
    }

    @Override
    protected void onDestroy() {
        instance=null;
        super.onDestroy();
    }
}
