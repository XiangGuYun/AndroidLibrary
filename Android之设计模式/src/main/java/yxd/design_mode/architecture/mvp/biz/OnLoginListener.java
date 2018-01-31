package yxd.design_mode.architecture.mvp.biz;

import yxd.design_mode.architecture.mvp.bean.User;

/**
 * Created by asus on 2017/12/19.
 */

public interface OnLoginListener
{
    void loginSuccess(User user);

    void loginFailed();
}
