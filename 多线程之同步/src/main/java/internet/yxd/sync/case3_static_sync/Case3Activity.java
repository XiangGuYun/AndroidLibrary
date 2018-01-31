package internet.yxd.sync.case3_static_sync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.sync.R;
/*
静态同步方法

如果是静态方法的情况（execute()和execute2()都加上static关键字），
即便是向两个线程传入不同的Example对象，这两个线程仍然是互相制约的，必须先执行完一个，再执行下一个。

结论：
如果某个synchronized方法是static的，那么当线程访问该方法时，它锁的并不是synchronized方法所在的对象，
而是synchronized方法所在的类所对应的Class对象。Java中，无论一个类有多少个对象，这些对象会对应唯一一个Class对象，
因此当线程分别访问同一个类的两个对象的两个static，synchronized方法时，它们的执行顺序也是顺序的，
也就是说一个线程先去执行方法，执行完毕后另一个线程才开始。
 */
public class Case3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);

        /*
        这个试验证明对静态同步方法会锁住整个类，
        即便是同一个类创建的不同实例，它们的同步方法也彼此具有约束力。
        因为整个类只有一把锁，而不是给每个类对象各分配一把锁。
         */
        Example example = new Example();

        Thread t1 = new Thread1(example);

        // 此处即便传入不同的对象，静态方法同步仍然不允许多个线程同时执行
        Example example1 = new Example();

        Thread t2 = new Thread2(example1);

        t1.start();
        t2.start();

    }
}
