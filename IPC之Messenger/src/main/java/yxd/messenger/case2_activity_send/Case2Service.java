package yxd.messenger.case2_activity_send;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

public class Case2Service extends Service {

    /*
    处理信使捎回来的书信
     */
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x123:
                    String activityText = msg.getData().getString("activity");
                    Log.d("Test", "服务端收到了前端的消息："+activityText);
                    break;
            }
        }
    };


    public Case2Service() {}

    @Override
    public IBinder onBind(Intent intent) {
        //向Activity派出信使
        return new Messenger(handler).getBinder();
    }
}
