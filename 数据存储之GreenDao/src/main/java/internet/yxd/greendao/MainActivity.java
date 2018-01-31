package internet.yxd.greendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/*
https://github.com/greenrobot/greenDAO
http://blog.csdn.net/qq_30034925/article/details/54729734

@Entity 用于标识这是一个需要Greendao帮我们生成代码的bean
@Id 标明主键，括号里可以指定是否自增
@Property 用于设置属性在数据库中的列名（默认不写就是保持一致）
@NotNull 非空
@Transient 标识这个字段是自定义的不会创建到数据库表里
@Unique 添加唯一约束
@ToOne 是将自己的一个属性与另一个表建立关联（外键）
@ToMany的属性referencedJoinProperty，类似于外键约束。
@JoinProperty 对于更复杂的关系，可以使用这个注解标明目标属性的源属性。
 */
public class MainActivity extends AppCompatActivity {

    private Query<User> userQuery;
    private UserDao userDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoSession session = ((App)getApplication()).getDaoSession();
        userDao = session.getUserDao();
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
    }

    public void insert(View view) {
        User user = new User(null,"jianguotang", "男",18,2000);
        userDao.insert(user);
    }

    public void delete(View view) {
        userDao.deleteByKey(5l);
    }

    public void update(View view) {
        //查询id是1位置的数据
        User user = userDao.load(5l);
        //对其进行修改
        user.setName("简国堂");
        userDao.update(user);
    }

    public void query(View view) {
        //查询全部数据
        List<User> users = userQuery.list();
        //按照属性name和sex来查询user
        QueryBuilder<User> builder = userDao.queryBuilder();
        Query<User> query = builder
                .where(UserDao.Properties.Name.eq("Jim"),
                        UserDao.Properties.Sex.eq("男"))
                .build();
        List<User> list = query.list();
    }
}
