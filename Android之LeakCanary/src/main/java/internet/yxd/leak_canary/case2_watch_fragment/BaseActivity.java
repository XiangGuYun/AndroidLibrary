package internet.yxd.leak_canary.case2_watch_fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.leak_canary.R;

public class BaseActivity extends FragmentActivity {

    private FragmentUtils utils;
    private LeakFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        fragment = new LeakFragment();
        utils = new FragmentUtils(this,fragment,R.id.fl_container);
    }
}
