package yxd.design_mode.architecture.mvp.view;

import yxd.design_mode.architecture.mvp.bean.User;

/**
 * Created by asus on 2017/12/19.
 */

public interface IUserLoginView
{
    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();

}
