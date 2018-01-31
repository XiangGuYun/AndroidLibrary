package yxd.service.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asus on 2017/12/13.
 */

public class DbHelper extends SQLiteOpenHelper {

    private String sql_create = "create table file(_id integer primary key autoincrement," +
            "thread_id integer,url text,start integer,end integer,process_value integer)";
    private String sql_drop = "drop table if exists file";

    public DbHelper(Context ctx){
        super(ctx, "file.db", null, 1);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sql_drop);
        db.execSQL(sql_create);
    }
}
