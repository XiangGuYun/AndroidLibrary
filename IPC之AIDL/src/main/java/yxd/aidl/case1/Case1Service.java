package yxd.aidl.case1;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class Case1Service extends Service {

    private List<String> wujiangs = new ArrayList<>();

    /*
    通过AIDL接口对象的Stub方法创建Binder对象，
    并实现接口中的抽象方法，以供其它进程调用
     */
    private Binder binder = new SanGuoInterface.Stub() {
        @Override
        public List<String> getWuJiangList() throws RemoteException {
            return wujiangs;
        }

        @Override
        public void addWujiang(String wj) throws RemoteException {
            wujiangs.add(wj);
        }
    };

    public Case1Service() {}

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化数据
        wujiangs.add("关羽");
        wujiangs.add("张飞");
        wujiangs.add("赵云");
    }

    /*
    将binder返回出去
     */
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
