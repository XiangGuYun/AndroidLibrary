package internet.yxd.leak_canary;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by asus on 2017/12/26.
 */

public class LeakApplication extends Application {
    //引用观察者
    private RefWatcher watcher;

    @Override public void onCreate() {
        super.onCreate();
       watcher = setUpLeakCanary();
    }
    /*
    设置LeakCanary来初始化引用观察者
     */
    private RefWatcher setUpLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)){
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    /*
    返回引用观察者
     */
    public static RefWatcher getRefWatcher(Context context){
        LeakApplication application = (LeakApplication) context.getApplicationContext();
        return  application.watcher;
    }
}
