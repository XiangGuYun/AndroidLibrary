package internet.yxd.eventbus.case2;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by asus on 2017/12/17.
 */

public class LeftFragment extends ListFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] contents = {
                "主线程消息1","子线程消息1","子线程消息2"
        };

        setListAdapter(new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1,
                contents
        ));

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        switch (position){
            case 0:
                Log.d("Test", "主线程发的消息1"+Thread.currentThread().getName());
                EventBus.getDefault().post(new MyEvent("主线程发的消息1"));
                break;
            case 1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Test", "子线程发的消息1"+Thread.currentThread().getName());
                        EventBus.getDefault().post(new MyEvent("子线程发的消息1"));
                    }
                }).start();
                break;
            case 2:
                Log.d("Test", "主线程发的消息2"+Thread.currentThread().getName());
                EventBus.getDefault().post(new MyEvent("主线程1发的消息2"));
                break;
        }

    }
}
