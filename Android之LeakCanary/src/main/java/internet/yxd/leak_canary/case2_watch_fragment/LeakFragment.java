package internet.yxd.leak_canary.case2_watch_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.leakcanary.RefWatcher;

import internet.yxd.leak_canary.LeakApplication;
import internet.yxd.leak_canary.R;
import internet.yxd.leak_canary.case1_watch_activity.MainActivity;

/**
 * Created by asus on 2018/1/8.
 */

public class LeakFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        LeakThread thread = new LeakThread();
        thread.start();

        return view;
    }

    /*
   非静态内部类LeakThread持有外部类MainActivity的引用，
   LeakThread中做了耗时操作，导致MainActivity无法被释放
    */
    private class LeakThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(6*10*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = LeakApplication.getRefWatcher(getActivity());
        watcher.watch(this);
    }

}
