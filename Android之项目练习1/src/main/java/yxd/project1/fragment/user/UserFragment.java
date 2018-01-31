package yxd.project1.fragment.user;

import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.listener.duowan.UserListener;
import yxd.project1.presenter.news.UserPresenter;

/**
 * Created by asus on 2017/12/31.
 */

public class UserFragment extends BaseFragment implements UserListener{

    public UserPresenter presenter;
    public Button btn_login, btn_signin;
    public TextInputLayout til_username, til_password;

    @Override
    protected void onCreateView(View view) {
        init();
    }


    private void init() {
        presenter = new UserPresenter(this, this);
        btn_login=id(R.id.btn_login);
        btn_signin=id(R.id.btn_signin);
        til_username=id(R.id.til_username);
        til_password=id(R.id.til_password);
        btn_login.setOnClickListener(presenter);
        btn_signin.setOnClickListener(presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

}
