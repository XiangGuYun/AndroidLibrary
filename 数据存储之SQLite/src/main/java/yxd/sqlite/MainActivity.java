package yxd.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
SQLite数据库数据类型
Integer varchar[3] float double char[10] text
SQL语句
创建表
create table 表名（字段名称 数据类型 约束，...）
create table person(_id Integer primary key,
name varchar[10], age Integer not null)
删除表
drop table 表名
drop table person
插入数据
insert into 表名(字段...) values(值...)
insert into person(_id,age) values(1, 20)
insert into person values(2, "zs", 30)
修改数据
update 表名 set 字段=新值 where 修改条件
update person set name="ls" where _id==1
删除数据
delete from 表名 where 删除条件
delete from person where _id=2
查询数据
select 字段名 from 表名 where 查询条件 group by 分组的字段 having 删选条件 order by 排序字段
select * from person
select _id,name from person
select * from person where _id-1
select * from person where _id<>1
select * from person where _id=1 and age>18
select * from person where name like "%小%"//小在中间
select * from person where name like "_小%"//小在最前面
select * from person where age between 10 and 20
select * from person where age>18 order by _id
数据库分页
select * from person limit 0,15;当前页面第一条数据的下标，每页显示的数据条目
 */
public class MainActivity extends AppCompatActivity {

    private MySQLiteHelper helper;
    EditText et_name, et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化SQLite助手
        helper = DbManager.getHelper(getApplicationContext());
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
    }

    /*
    点击按钮创建数据库
     */
    public void create_db(View view) {
        /*
        默认情况下都打开可读可写的数据库，只有在磁盘已满或数据库本身权限等情况下，
        getReadableDatabase()打开的是只读数据库
         */
        SQLiteDatabase db = helper.getReadableDatabase();//创建数据库
    }

    /*
    插入数据
     */
    public void insert(View view) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        SQLiteDatabase db = helper.getWritableDatabase();
        insertByApi(db, name, age);
        //insertBySql(db, name, age);
        db.close();
    }

    private void insertBySql(SQLiteDatabase db, String name, String age) {
        String sql = "insert into "+Constant.TABLE_NAME+"(name,age) " +
                "values(" +"'"+name+"'"+"," +Integer.parseInt(age)+")";
        Log.d("Test", "-----"+sql);
        db.execSQL(sql);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    private void insertByApi(SQLiteDatabase db, String name, String age) {
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, name);
        values.put(Constant.AGE, Integer.parseInt(age));
        long result = db.insert(Constant.TABLE_NAME, null,values);
        if(result>0){
            Toast.makeText(this, "插入成功（API）", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "插入失败（API）", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    更新数据
     */
    public void update(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        updateByApi(db);
        //updateBySql(db);
        db.close();
    }

    private void updateBySql(SQLiteDatabase db) {
        String sql = "update person set name='jim' and age=12 where _id=1";
        db.execSQL(sql);
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
    }

    private void updateByApi(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, "Green");
        //values.put(Constant.AGE, 19);
        long result = db.update(Constant.TABLE_NAME, values, "name=?", new String[]{"jim"});
        //db.update(Constant.TABLE_NAME, values, "_id=3", null);//效果与上方一致
        if(result>0){
            Toast.makeText(this, "修改成功（API）", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "修改失败（API）", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    删除数据
     */
    public void delete(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        deleteByApi(db);
        //deleteBySql(db);
        db.close();
    }

    private void deleteByApi(SQLiteDatabase db) {
        int result = db.delete(Constant.TABLE_NAME, "_id=?", new String[]{"3"});
        if(result>0){
            Toast.makeText(this, "删除成功（API）", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "删除失败（API）", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteBySql(SQLiteDatabase db) {
        String sql = "delete from person where _id=1";
        db.execSQL(sql);
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    /*
    查询数据
     */
    public void query(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //queryBySql(db);
        queryByApi(db);
        db.close();
    }

    private void queryByApi(SQLiteDatabase db) {
        /*
        查询表名
        查询字段数组
        查询条件
        查询条件占位符取值
        分组条件
        筛选条件
        排序条件
         */
        Cursor cursor = db.query(Constant.TABLE_NAME,null,null,
                null, null,null,
                "_id desc");//降序排序
        List<Person> people = cursorToList(cursor);
        for (int i = 0; i < people.size(); i++) {
            Log.d("Test", "API "+people.get(i).toString());
        }
    }

    private void queryBySql(SQLiteDatabase db) {
        String sql = "select * from person";
        Cursor cursor = db.rawQuery(sql, null);
        List<Person> people = cursorToList(cursor);
        for (int i = 0; i < people.size(); i++) {
            Log.d("Test", people.get(i).toString());
        }
    }

    private List<Person> cursorToList(Cursor cursor) {
        List<Person> people = new ArrayList<>();
        while (cursor.moveToNext()){//表示查询到了记录
            int _id = cursor.getInt(0);//根据字段下标获取值
            //int _id = cursor.getInt(cursor.getColumnIndex(Constant._ID));
            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));
            Person person = new Person();
            person.set_id(_id);
            person.setName(name);
            person.setAge(age);
            people.add(person);
        }
        return people;
    }

    /*
    批量插入（事务）
     */
    public void transaction(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        //开启事务
        db.beginTransaction();
        for (int i = 0; i < 100; i++) {
            String sql = "insert into person values('小牧', 18)";
            db.execSQL(sql);
        }
        //提交事务
        db.setTransactionSuccessful();
        //关闭事务
        db.endTransaction();
        db.close();
    }
}
