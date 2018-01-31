package yxd.project1.presenter.news;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import yxd.project1.R;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.bean.DaoSession;
import yxd.project1.bean.User;
import yxd.project1.bean.UserDao;
import yxd.project1.constant.Constant;
import yxd.project1.context.MainActivity;
import yxd.project1.context.MyApplication;
import yxd.project1.fragment.user.UserFragment;
import yxd.project1.fragment.user.UserInfoFragment;
import yxd.project1.listener.duowan.UserListener;
import yxd.project1.utils.EditTextUtils;

/**
 * Created by asus on 2018/1/4.
 */

public class UserPresenter implements View.OnClickListener{

    private Query<User> userQuery;
    private UserListener listener;
    private UserFragment frag;
    private UserDao userDao;
    private MainActivity ctx;

    public UserPresenter(UserListener listener, Fragment fragment) {
        this.listener = listener;
        frag = (UserFragment) fragment;
        DaoSession session = ((MyApplication)frag.getActivity().getApplication()).getDaoSession();
        userDao = session.getUserDao();
        userQuery = userDao.queryBuilder().orderAsc(UserDao.Properties.Id).build();
        ctx = (MainActivity) frag.getActivity();
    }

    @Override
    public void onClick(View view) {
        String username = frag.til_username.getEditText().getText().toString();
        String password = frag.til_password.getEditText().getText().toString();
        switch (view.getId()){
            case R.id.btn_login://登录按钮
                if(EditTextUtils.verifyUsername(frag.til_username, username)
                        && EditTextUtils.verifyPassword(frag.til_password, password)){
                    List<User> list = userDao.queryBuilder().where(UserDao.Properties.Username.eq(username),
                            UserDao.Properties.Password.eq(password)).build().list();
                    if(list.size()>0){
                        Constant.IS_LOGIN=true;
                        SPUtils.setParam(ctx, Constant.CURRENT_USERNAME, username);
                        toast("登录成功！");
                        ctx.userInfoFragment=new UserInfoFragment(username);
                        ctx.getFragmentUtils().switchFragment(ctx.userInfoFragment);
                    }else {
                        if (userDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).list().size()==0){
                            toast("用户名不存在请先注册！");
                        }else if(userDao.queryBuilder().where(UserDao.Properties.Password.eq(password)).list().size()==0){
                            toast("密码错误！");
                        }
                    }
                }
                break;
            case R.id.btn_signin://注册按钮
                if(EditTextUtils.verifyUsername(frag.til_username, username)
                        && EditTextUtils.verifyPassword(frag.til_password, password)){
                    //按照条件来查询user
                    List<User> list = userDao.queryBuilder()
                            .where(UserDao.Properties.Username.eq(username)).build().list();
                    if(list.size()>0){
                        toast("用户名已存在！");
                    }else {
                        User user = new User(null, username, password);
                        userDao.insert(user);
                        toast("注册成功");
                    }
                }
                break;
        }
    }


    public void toast(String text){
        Toast.makeText(frag.getActivity(), text, Toast.LENGTH_SHORT).show();
    }

}
