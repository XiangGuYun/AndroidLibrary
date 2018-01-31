package yxd.design_mode.architecture.mvp.presentor;

import android.os.Handler;

import yxd.design_mode.architecture.mvp.bean.User;
import yxd.design_mode.architecture.mvp.biz.IUserBiz;
import yxd.design_mode.architecture.mvp.biz.OnLoginListener;
import yxd.design_mode.architecture.mvp.biz.UserBiz;
import yxd.design_mode.architecture.mvp.view.IUserLoginView;

/**
 * Created by asus on 2017/12/19.
 */

public class UserLoginPresenter
{
    private IUserBiz userBiz;
    private IUserLoginView userLoginView;
    private Handler mHandler = new Handler();

    public UserLoginPresenter(IUserLoginView userLoginView)
    {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }

    public void login()
    {
        userLoginView.showLoading();
        userBiz.login(userLoginView.getUserName(), userLoginView.getPassword(), new OnLoginListener()
        {
            @Override
            public void loginSuccess(final User user)
            {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.toMainActivity(user);
                        userLoginView.hideLoading();
                    }
                });

            }

            @Override
            public void loginFailed()
            {
                //需要在UI线程执行
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        userLoginView.showFailedError();
                        userLoginView.hideLoading();
                    }
                });

            }
        });
    }

    public void clear()
    {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }



}
