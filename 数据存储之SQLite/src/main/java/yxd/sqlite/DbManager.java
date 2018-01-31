package yxd.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/**
 * Created by asus on 2017/12/13.
 */

public class DbManager {

   private static MySQLiteHelper helper;

   private DbManager(){

   }

   public static MySQLiteHelper getHelper(Context ctx){
       if(helper == null){
           helper = new MySQLiteHelper(ctx);
       }
       return helper;
   }

   public static void execSQL(SQLiteDatabase db, String sql){
       if(db != null){
           if(!TextUtils.isEmpty(sql)){
               db.execSQL(sql);
           }
       }
   }
}
