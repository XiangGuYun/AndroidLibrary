package yxd.messenger.case1_base;

import android.annotation.SuppressLint;
import android.content.ComponentName;
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
import android.view.View;

import yxd.messenger.R;

/*
http://blog.csdn.net/itachi85/article/details/50448409
Messenger可以在不同进程中传递Message对象，
我们在Message中加入我们想要传的数据就可以在进程间的进行数据传递了。
Messenger是一种轻量级的IPC方案并对AIDL 进行了封装，它实现起来比较容易。
 */
/*
接下来创建客户端(MainActivity.java)，绑定另一个进程的servce，
绑定成功以后根据服务端返回的Binder对象创建Messenger，并用Messenger向服务端发送信息。
*/
public class MainActivity extends AppCompatActivity {

    /*流程简介
    ①：Service向Activity派出信使
    ②：Activity将书信（Message）交给Service的试着让其捎带回去
    ③：Service使用驿站（Handler）来接收信使捎回来的书信并进行处理
     */
    private Messenger messenger;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
            Message message = Message.obtain(null, 0x123);
            Bundle bundle = new Bundle();
            bundle.putString("msg", "这是一条来自客户端的信息");
            message.setData(bundle);
             /*
            现在我们加上这段代码将Messenger对象传给服务端，当然需要关联我们定义的Handler
             */
            //将Messenger传递给服务端
            message.replyTo=new Messenger(handler);
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /*
    客户端需要创建一个Handler来接收服务端的信息
    */
    @SuppressLint("HandlerLeak")
    static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    Log.d("Test", "收到了服务器的消息 "+msg.getData().get("msg"));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    public void bind(View view) {
        Intent intent = new Intent(this, MessengerService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
}
