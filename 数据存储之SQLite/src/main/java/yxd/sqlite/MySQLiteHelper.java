package yxd.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by asus on 2017/12/13.
 */

public class MySQLiteHelper extends SQLiteOpenHelper{

    private static final String TEST = "Test";
    private Context ctx;

    /*
    调用一个参数的构造方法来创建SQLite助手
     */
    public MySQLiteHelper(Context ctx){
        super(ctx, Constant.DB_NAME, null, Constant.DB_VERSION);
        this.ctx = ctx;
    }

    /**
     *
     * @param context
     * @param name 数据库库名
     * @param factory 游标工厂
     * @param version 创建的数据库版本 >=1
     */
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,
                          DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
    /*
    数据库创建
    同一数据库版本只会执行一次
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TEST, "--------------onCreate------------");
        String sql_create_db = "create table "+
                Constant.TABLE_NAME+"("+
                Constant._ID+", Integer primary key," +
                Constant.NAME+" varchar[10]," +
                Constant.AGE+" Integer)";
        db.execSQL(sql_create_db);
        Toast.makeText(ctx, "创建了数据库", Toast.LENGTH_SHORT).show();
    }
    /*
    数据库更新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TEST, "--------------onUpgrade------------");
        Toast.makeText(ctx, "打开了数据库", Toast.LENGTH_SHORT).show();

    }
    /*
    数据库打开
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(TEST, "--------------onOpen------------");

    }

}
