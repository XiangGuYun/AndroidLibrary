package internet.yxd.greendao;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

/**
 * Created by asus on 2017/12/13.
 */

public class App extends Application {

    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;
    /*
    防止重复生成数据库
     */
    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new  DaoMaster.DevOpenHelper
                (this, ENCRYPTED ? "users-db-encrypted" : "users-db");
        Database db =  helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
