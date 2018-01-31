package yxd.aidl.case1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import yxd.aidl.R;

public class Case1Activity extends AppCompatActivity {

    private ServiceConnection conn;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case1);
        //创建Intent
        intent = new Intent(this, Case1Service.class);
        //创建服务连接
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //通过服务来获取AIDL接口对象
                SanGuoInterface sanGuoInterface = SanGuoInterface.Stub.asInterface(service);
                try {
                    //调用AIDL接口方法
                    sanGuoInterface.addWujiang("马超");
                    //打印出列表信息
                    for (int i = 0; i < sanGuoInterface.getWuJiangList().size(); i++) {
                        Log.d("Test", sanGuoInterface.getWuJiangList().get(i));
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };


    }
    /*
    点击按钮，绑定服务
     */
    public void bind(View view) {
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}
