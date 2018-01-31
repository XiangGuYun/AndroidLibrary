package internet.yxd.marshmallow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    /*
    权限检查
     */
    public boolean checkPM(String...permissions){
        for (String pm:permissions
             ) {
            if(ContextCompat.checkSelfPermission(this, pm)
                    != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return  true;
    }

    /*
    权限申请
     */
    public void requestPM(int code, String...permissions){
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case Constant.CALL_PHONE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call(this);
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case Constant.WRITE_EXTERNAL_STORAGE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sdcard();
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    protected void call(Activity ctx) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:"+10086);
        intent.setData(data);
        ctx.startActivity(intent);
    }

    protected void sdcard() {
        FileOutputStream fileOutputStream = null;
        PrintWriter writer = null;
        String pathName = Environment.getExternalStorageDirectory().toString()+"/test.txt";
        try {
            fileOutputStream = new FileOutputStream(new File(pathName));
            writer = new PrintWriter(fileOutputStream);
            writer.write("This is a text");
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "异常", Toast.LENGTH_SHORT).show();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

}
