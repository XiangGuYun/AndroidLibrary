package yxd.activity;

import android.annotation.SuppressLint;
import android.view.View;

import yxd.activity.base.context.BaseFragment;

/**
 * Created by asus on 2017/12/20.
 */

@SuppressLint("ValidFragment")
public class MainFragment extends BaseFragment {

    String title;

    @SuppressLint("ValidFragment")
    public MainFragment(String text){
        title = text;
    }

    @Override
    protected void onCreateView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

}
