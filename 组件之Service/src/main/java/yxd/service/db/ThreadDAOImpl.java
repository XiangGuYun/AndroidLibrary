package yxd.service.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import yxd.service.bean.ThreadInfo;

/**
 * Created by asus on 2017/12/13.
 */
/*
数据访问接口的实现
 */
public class ThreadDAOImpl implements ThreadDAO{

    private static final String TABLE_NAME = "file";
    private DbHelper dbHelper;
    private static final String KEY1 = "thread_id";
    private static final String KEY2 = "url";
    private static final String KEY3 = "start";
    private static final String KEY4 = "end";
    private static final String KEY5 = "process_value";

    public ThreadDAOImpl(Context ctx){
        dbHelper = new DbHelper(ctx);
    }

    @Override
    public void insertThread(ThreadInfo info) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY1, info.getId());
        values.put(KEY2, info.getUrl());
        values.put(KEY3, info.getStart());
        values.put(KEY4, info.getEnd());
        values.put(KEY5, info.getProcessValue());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void deleteThread(String url, int thread_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, "url=? and thread_id=?", new String[]{url, thread_id+""});
        db.close();
    }

    @Override
    public void updateThread(String url, int thread_id, int process_value) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY5, process_value);
        db.update(TABLE_NAME, values, "url=? and thread_id=?", new String[]{url, thread_id+""});
        db.close();
    }

    @Override
    public List<ThreadInfo> getThread(String url) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "url=?",new String[]{url},
                null, null, null);
        List<ThreadInfo> infos = cursorToList(cursor);
        db.close();
        return infos;
    }

    private List<ThreadInfo> cursorToList(Cursor cursor) {
        List<ThreadInfo> infos = new ArrayList<>();
        while (cursor.moveToNext()){
            ThreadInfo info = new ThreadInfo();
            info.setId(cursor.getInt(cursor.getColumnIndex(KEY1)));
            info.setUrl(cursor.getString(cursor.getColumnIndex(KEY2)));
            info.setStart(cursor.getInt(cursor.getColumnIndex(KEY3)));
            info.setEnd(cursor.getInt(cursor.getColumnIndex(KEY4)));
            info.setProcessValue(cursor.getInt(cursor.getColumnIndex(KEY5)));
            infos.add(info);
        }
        cursor.close();
        return infos;
    }

    @Override
    public boolean isExists(String url, int thread_id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "url=? and thread_id=?",
                new String[]{url, thread_id+""},
                null, null, null);
        boolean exists = cursor.moveToNext();
        db.close();
        cursor.close();
        return exists;
    }
}
