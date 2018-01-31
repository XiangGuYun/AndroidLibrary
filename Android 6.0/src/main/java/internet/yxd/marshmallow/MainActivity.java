package internet.yxd.marshmallow;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/*
检查危险权限组
adb shell pm list permissions -d -g

 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void call(View view) {
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE
        )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 1);
        }else{
            doCall(this);
        }
    }

    @SuppressLint("MissingPermission")
    protected void doCall(Activity ctx) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:"+10086);
        intent.setData(data);
        ctx.startActivity(intent);
    }

    public void sdcard(View view) {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }else {
            doSdcard();
        }
    }

    protected void doSdcard() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call(this);
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sdcard();
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
