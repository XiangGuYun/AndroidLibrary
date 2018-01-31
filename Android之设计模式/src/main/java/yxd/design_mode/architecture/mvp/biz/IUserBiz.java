package yxd.design_mode.architecture.mvp.biz;

/**
 * Created by asus on 2017/12/19.
 */

public interface IUserBiz
{
    void login(String username, String password, OnLoginListener loginListener);
}
