package yxd.project1.context;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.database.Database;

import yxd.project1.bean.DaoMaster;
import yxd.project1.bean.DaoSession;
import okhttp3.OkHttpClient;

/**
 * Created by asus on 2018/1/1.
 */

public class MyApplication extends Application {
    public static RequestQueue queue;
    private static Context context;
    private static OkHttpClient okHttpClient;

    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    private RefWatcher watcher;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        queue = Volley.newRequestQueue(getApplicationContext());
        okHttpClient = new OkHttpClient();

        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper
                (this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

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
        MyApplication application = (MyApplication) context.getApplicationContext();
        return  application.watcher;
    }

    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }

    public static RequestQueue getQueue(){
        return queue;
    }

    public static Context getContext() {
        return context;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
