package internet.yxd.process_alive.case1_onepixel_activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static internet.yxd.process_alive.case1_onepixel_activity.OnePixelActivity.instance;

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       String action = intent.getAction();
       switch (action){
           case Intent.ACTION_SCREEN_ON://该广播接收器必须动态注册才能接受此ACTION！
               if(instance != null) instance.finish();
               break;
           case Intent.ACTION_SCREEN_OFF://该广播接收器必须动态注册才能接受此ACTION！
               Intent intent1 = new Intent(context, OnePixelActivity.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               context.startActivity(intent);
               break;
       }
    }
}
