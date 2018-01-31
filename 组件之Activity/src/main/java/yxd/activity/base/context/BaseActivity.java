package yxd.activity.base.context;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import yxd.activity.base.common.PermissionConstant;

/**
 * 托管所有Fragment的Activity，程序的入口
 */
public abstract class BaseActivity extends ActivitySubject {

    public enum BACKFLAG{
        NEED_TO, MAIN_FRAGEMNT, SECOND_FRAGMENT;
    }
    public FragmentUtils fragmentUtils;

    //收藏所有Fragment的集合
    public List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        fragments = getFragments();
        fragmentUtils = new FragmentUtils(this, fragments, getFragmentContainerId());
        onCreate(fragmentUtils);
    }

    public abstract void onCreate(FragmentUtils fragmentUtils);

    public abstract int getLayoutId();

    public abstract List<Fragment> getFragments();

    public abstract int getFragmentContainerId();


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();//将最上层Fragment弹出回退栈
//        switch (BACKFLAG.NEED_TO) {//根据回退标志来决定返回到哪个Fragment
//            case MAIN_FRAGEMNT:
//                fragmentUtils.switchFragment(mf);
//                break;
//            case SECOND_FRAGMENT:
//
//                break;
//        }
    }

    @Override
    public void update() {

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
            case PermissionConstant.CALL_PHONE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    call(this);
                }else {
                    Toast.makeText(this, "权限授予未成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case PermissionConstant.WRITE_EXTERNAL_STORAGE:
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
