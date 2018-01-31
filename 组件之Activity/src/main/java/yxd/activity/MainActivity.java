package yxd.activity;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import yxd.activity.base.context.BaseActivity;
import yxd.activity.base.context.FragmentUtils;

public class MainActivity extends BaseActivity {

    List<Fragment> fragments = new ArrayList<>();
    List<Fragment> btnFragments = new ArrayList<>();
    FragmentUtils btnUtils;

    @Override
    public void onCreate(FragmentUtils fragmentUtils) {
        btnFragments.add(new BtnFragment());
        btnUtils = new FragmentUtils(this, btnFragments, R.id.fl_btn_container);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public List<Fragment> getFragments() {
        fragments.add(new MainFragment("Hello Fragment1"));
//        fragments.add(new MainFragment("Hello Fragment2"));
//        fragments.add(new MainFragment("Hello Fragment3"));
        return fragments;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.fl_container;
    }

//    public void btn1(View view) {
//        fragmentUtils.switchFragment(fragments.get(0));
//    }
//
//    public void btn2(View view) {
//        fragmentUtils.switchFragment(fragments.get(1));
//    }
//
//    public void btn3(View view) {
//        fragmentUtils.switchFragment(fragments.get(2));
//    }
}
