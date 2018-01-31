package yxd.project1.base.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;

import java.lang.ref.WeakReference;


/**
 * Created by asus on 2017/12/20.
 */

public class HandlerUtils {

    private static HandlerListner handlerListner;
    private HandlerThread thread;
    private static Handler handler;

    public HandlerUtils(String tName, int priority, HandlerListner listner){
        handlerListner = listner;
        thread = new HandlerThread(tName);
        thread.start();
        handler  = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Process.setThreadPriority(priority);
                handlerListner.handleMessage(msg);
            }
        };
    }

    public <T> T getWeakReference(T t){
        return new WeakReference<>(t).get();
    }

    public Handler getHandler(){
        return handler;
    }

    public void clear(){
        if(thread != null)
            thread.quit();
        if(handler != null)
            handler.removeCallbacksAndMessages(null);
    }

    public interface HandlerListner{
        void handleMessage(Message message);
    }


}
