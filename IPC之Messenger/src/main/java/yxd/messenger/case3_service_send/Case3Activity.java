package yxd.messenger.case3_service_send;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import yxd.messenger.R;
import yxd.messenger.case2_activity_send.Case2Service;
/*
前后端都有一个Messenger并绑定了各自的Handler。

后端向前端发消息必须通过前端的Messenger
前端向后端发消息也必须通过后端的Messenger

前端如何获得后端的Messenger？
通过后端onBind返回，然后前端在conn中获取。

后端如何获得前端的Messenger？
前端获得后端的Messenger后将自己的Messenger作为Message的replyTo传到后端。

如何互发消息？
前后端获得了对方的Messenger之后，创建Message由Messenger发送出去即可。
由于Messenger都绑定了各自的Handler，所以接收方可以自己的Handler中处理发送方的消息。

 */
public class Case3Activity extends AppCompatActivity {
    /*流程简介
    ①：Service向Activity派出信使
    ②：Activity让自己的信使跟着Service的信使一起回去
    ③：Service将书信（Message）交给Activity的信使，让其捎带回去
    ④：Activity使用驿站（Handler）来接收信使捎回来的书信并进行处理
     */
    private Intent intent;
    private ServiceConnection conn;
    private Messenger messenger;

    /*
    处理信使捎回来的书信
     */
    @SuppressLint("HandlerLeak")
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    Log.d("Test", "前端收到了服务端的消息："+
                            msg.getData().getString("service"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);

        intent = new Intent(this, Case3Service.class);

        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                //获取Service派来的信使
                messenger = new Messenger(service);
                Message message = Message.obtain(null, 0x111);
                message.replyTo = new Messenger(handler);
                try {
                    //让自己的信使跟着Service派来的信使一起回去
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
}
