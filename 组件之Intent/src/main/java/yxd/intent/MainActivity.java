package yxd.intent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        1、component(组件)：目的组件
        Component属性明确指定Intent的目标组件的类名称。（属于直接Intent）
        如果 component这个属性有指定的话，将直接使用它指定的组件。指定了这个属性以后，Intent的其它所有属性都是可选的。
         */
//        Intent intent = new Intent();
//        //创建组件，通过组件来响应
//        ComponentName component = new ComponentName(MainActivity.this, SecondActivity.class);
//        intent.setComponent(component);
//        startActivity(intent);


    }

    /*
    创建隐式Intent
     */
    public void implicit(View view) {
        Uri number = Uri.parse("tel:5551234");
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }

    /*
    判断系统是否有activity能处理intent
     */
    public void isHave(View view) {
        // 判断是否有activity能接收intent请求
        Intent intent = new Intent(this, SecondActivity.class);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
        boolean isIntentSave = activities.size()>0;
        Toast.makeText(this, isIntentSave + "", Toast.LENGTH_SHORT).show();
    }

    /*
    使用 createChooser 方法获取选择启动activity列表
     */
    public void createChooser(View v){
        Uri uri = Uri.parse("tel:55551234");
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);

// 判断是否有activity能接收intent请求
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
        boolean isIntentSave = activities.size()>0;

        String title = "Choose App";
        Intent chooser = Intent.createChooser(intent, title);
        startActivity(chooser);
    }
}
