package yxd.messenger.case1_base;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    Log.i("Test","收到客户端信息-------"+msg.getData().get("msg"));
                    //得到客户端传来的Messenger对象
                    Messenger messenger = msg.replyTo;
                    //获取what为0x123的Message
                    Message message = Message.obtain(null, 0x123);
                    Bundle bundle = new Bundle();
                    bundle.putString("msg", "这里是服务端，收到消息了");
                    message.setData(bundle);
                    Log.d("Test", "line1");
                    try {
                        //通过Messenger发送给客户端
                        messenger.send(message);
                        Log.d("Test", "line2");

                    } catch (RemoteException e) {
                        e.printStackTrace();
                        Log.d("Test", "line3"+e.getMessage());

                    }
                    break;
            }
        }
    };

    public MessengerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //在onBind方法中创建Messenger，
        //关联接收消息的Handler
        //调用getBinder来获取Binder对象
        return new Messenger(handler).getBinder();
    }
}
