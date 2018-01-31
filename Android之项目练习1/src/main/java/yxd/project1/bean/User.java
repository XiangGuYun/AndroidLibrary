package yxd.project1.bean;

/**
 * Created by asus on 2018/1/12.
 */

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

/**
 * Created by asus on 2017/12/13.
 */
/*
在最顶部有一个@Entity，这个标志告诉greenDao,这个是我需要生成的表。
接着点击 Build -> Make Project（快捷键ctrl+F9）,将会自动为我们生成需要的类和代码
 */
@Keep
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String username;
    private String password;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

