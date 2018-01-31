package yxd.messenger.case2_activity_send;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.messenger.R;
/*
Activity向Service发送Messenger
 */
public class Case2Activity extends AppCompatActivity {

    private ServiceConnection conn;
    private Intent intent;
    //可以理解为信使
    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        //启动服务的意图
        intent = new Intent(this, Case2Service.class);
        //服务连接
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //获取Service的派来的信使
                messenger = new Messenger(service);
                //创建书信
                Message message = Message.obtain(null, 0x123);
                Bundle bundle = new Bundle();//一定要用Bundle，不能用obj!
                bundle.putString("activity", "这里是前端，请求服务端支援！");
                //将信息写入书信
                message.setData(bundle);
                try {
                    //将书信交给来使，让他捎回去
                    messenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(intent, conn, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        //取绑服务
        unbindService(conn);
        super.onDestroy();
    }
}
