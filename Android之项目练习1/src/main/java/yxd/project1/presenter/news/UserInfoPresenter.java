package yxd.project1.presenter.news;

import android.view.View;

import yxd.project1.R;
import yxd.project1.base.storage.SPUtils;
import yxd.project1.constant.Constant;
import yxd.project1.context.MainActivity;
import yxd.project1.fragment.user.UserFragment;
import yxd.project1.fragment.user.UserInfoFragment;
import yxd.project1.listener.duowan.UserInfoListener;

/**
 * Created by asus on 2018/1/4.
 */

public class UserInfoPresenter implements View.OnClickListener {

    private UserInfoListener listener;
    private UserInfoFragment frag;
    private MainActivity ctx;
    private UserFragment fragment;

    public UserInfoPresenter(UserInfoFragment fragment, UserInfoListener listener) {
        this.listener = listener;
        frag = fragment;
        ctx = (MainActivity) frag.getActivity();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_logout:
                Constant.IS_LOGIN=false;
                SPUtils.setParam(ctx, Constant.CURRENT_USERNAME, "");
                if(fragment==null)
                    fragment = new UserFragment();
                ctx.getFragmentUtils().switchFragment(fragment);
                break;
        }
    }
}
