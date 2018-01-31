package internet.yxd.eventbus.case2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import internet.yxd.eventbus.R;

/**
 * Created by asus on 2017/12/17.
 */

public class RightFragment extends Fragment {

    TextView tv_right;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_right,
                container, false);
        tv_right = v.findViewById(R.id.tv_right);
        return v;
    }

    /*
    与发布者运行在同一个线程
     */
    @Subscribe
    public void onEvent(MyEvent myEvent){

    }

    /*
    运行在主线程中
     */
    @Subscribe
    public void onMainEvent(MyEvent myEvent){

    }

    /*
    执行在一个新的线程
     */
    @Subscribe
    public void onEventAysnc(MyEvent event){

    }

    /*
    执行在子线程，如果发布者是在子线程则直接运行，否则创建一条子线程运行
     */
    @Subscribe
    public void onEventBackgroundThread(MyEvent event){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
