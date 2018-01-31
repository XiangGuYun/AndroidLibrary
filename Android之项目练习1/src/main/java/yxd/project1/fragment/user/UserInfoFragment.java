package yxd.project1.fragment.user;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import yxd.project1.R;
import yxd.project1.base.context.BaseFragment;
import yxd.project1.listener.duowan.UserInfoListener;
import yxd.project1.presenter.news.UserInfoPresenter;

/**
 * Created by asus on 2018/1/4.
 */

@SuppressLint("ValidFragment")
public class UserInfoFragment extends BaseFragment implements UserInfoListener{

    private String username;
    private TextView tv_user_info;
    private Button btn_logout;
    private UserInfoPresenter presenter;

    @SuppressLint("ValidFragment")
    public UserInfoFragment(String username){
        this.username=username;
    }

    @Override
    protected void onCreateView(View view) {
        init();
        tv_user_info.setText(username);
    }

    private void init() {
        tv_user_info=id(R.id.tv_user_info);
        btn_logout=id(R.id.btn_logout);
        presenter = new UserInfoPresenter(this, this);
        btn_logout.setOnClickListener(presenter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_info;
    }
}
