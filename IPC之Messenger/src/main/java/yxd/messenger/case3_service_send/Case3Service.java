package yxd.messenger.case3_service_send;

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
/*
以Servcie作为发送方
 */
public class Case3Service extends Service {

    private Messenger messenger;

    /*
    处理Activity的回信
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x111:
                    //获取Activity派来的信使
                    messenger = msg.replyTo;
                    Message message = Message.obtain(null, 0x123);
                    //创建书信
                    Bundle bundle = new Bundle();
                    bundle.putString("service", "这是来自服务端的消息，前端请坚守！");
                    message.setData(bundle);
                    try {
                        //将书信交给Activity的信使让其捎带回去
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public Case3Service() {}

    /*
    向Activity派出信使
     */
    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(handler).getBinder();
    }
}
