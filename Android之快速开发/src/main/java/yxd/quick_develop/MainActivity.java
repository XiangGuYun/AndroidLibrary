package yxd.quick_develop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import yxd.quick_develop.base.context.BaseActivity;
import yxd.quick_develop.base.context.FragmentUtils;

public class MainActivity extends BaseActivity {


    @Override
    protected void addFragments(List<Fragment> fragments) {
        fragments.add(new MainFragment());
    }

    @Override
    public void onCreate(FragmentUtils fragmentUtils) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public int getFragmentContainerId() {
        return R.id.fl_container;
    }
}
