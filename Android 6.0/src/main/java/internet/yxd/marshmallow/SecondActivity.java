package internet.yxd.marshmallow;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/*
对申请权限的操作进行封装
 */
public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

    }

    public void call(View view) {

        if(!checkPM(Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            requestPM(Constant.CALL_PHONE, Manifest.permission.CALL_PHONE);
        }else{
            call(this);
        }
    }

    public void sdcard(View view) {

        if(!checkPM(Manifest.permission.CALL_PHONE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            requestPM(Constant.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else {
            sdcard();
        }
    }
}
