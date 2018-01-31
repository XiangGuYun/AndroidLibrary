package yxd.test.optimize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import yxd.test.L;

/**
 * Created by asus on 2018/1/9.
 */

public class CodeOptimizeTest {

    public static final String PERSON = "花花";
    enum MyEnum{
        PERSON("小明");
        private String name;

        MyEnum(String name) {
            this.name = name;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void main(){

        /*
        字符串拼接优化
         */
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strList.add("TIEM"+(i+1));
        }
        //---------------------------------------------
        StringBuilder builder = new StringBuilder("");
        for (String str:strList
             ) {
            builder.append(str);
        }
        L.d(builder.toString());
        //---------------------------------------------

        /*
        创建Person类的软引用和弱引用，并获取其实例
         */
        class Person{
            private String name;
            public Person(String name) {
                this.name = name;
            }
        }
        //---------------------------------------------
        SoftReference<Person> softReference = new SoftReference<>(new Person("小明"));
        WeakReference<Person> weakReference = new WeakReference<>(new Person("小红"));
        Person person = softReference.get();
        Person person1 = weakReference.get();
        L.d(person.name+" "+person1.name);
        //---------------------------------------------

        /*
        在main方法外分别使用static finale和枚举来声明字符串常量PERSON，并打印日志输出
         */
        //---------------------------------------------
        Log.d("Test", PERSON+"----"+MyEnum.PERSON.name);
        //---------------------------------------------

        /*
        用SurfaceView写一个画板DrawingBoard
         */

        /*
        使用Android的容器类SparseArray和ArrayMap添加”小红“”小明“”吉姆“三个Person并遍历打印
         */
        SparseArray<Person> array = new SparseArray<>();
        array.put(1, new Person("小红"));
        array.put(2, new Person("小明"));
        array.put(3, new Person("吉姆"));
        ArrayMap<String, Person> map = new ArrayMap<>();
        map.put("小红", new Person("小红"));
        map.put("小明", new Person("小明"));
        map.put("吉姆", new Person("吉姆"));
        for (int i = 0; i < array.size(); i++) {
            L.d("SparseArray："+array.get(i+1).name);
            L.d("ArrayMap"+map.valueAt(array.size()-1-i).name);
        }



    }

}


