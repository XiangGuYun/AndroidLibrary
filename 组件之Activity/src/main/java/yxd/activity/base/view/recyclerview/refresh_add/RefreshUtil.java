package yxd.activity.base.view.recyclerview.refresh_add;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
/*
        List<String> list = Arrays.asList("1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1",
        "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2", "1", "2");

        lv.setAdapter(new CommonAdapter(getActivity(), list,
                R.layout.recyclerview_cell, (holder, position) -> {
                    holder.setText(R.id.tv_cell, list.get(position));
                }));

        getRefreshUtil(() -> ints(VIEWGROUP_MATCH, VIEWGROUP_MATCH, 1))
                .addView(lv, () -> RxJava.thread((msg)->{
                    switch (msg.what){
                        case 0x123:
                            toast("正在刷新...");
                            break;
                        case 0x111:
                            RefreshUtil.setRefreshing(false);
                            break;
                    }
                },(sub)->{
                    sub.onNext(RxJava.getEmptyMsg(0x123));
                    RxJava.sleep(3000);
                    sub.onNext(RxJava.getEmptyMsg(0x111));
                }));
 */

/**
 * Created by asus on 2016/7/26.
 */
public class RefreshUtil{

    private static SwipeRefreshLayout refreshLayout;
    private ViewGroup parent;
    private getViewParams params;

    public static void setRefreshing(boolean refreshing){
        if(refreshLayout != null)
        refreshLayout.setRefreshing(refreshing);
    }

    public RefreshUtil(Context context, View v, getViewParams params){
        this.params = params;
        refreshLayout = new SwipeRefreshLayout(context);
        parent = (ViewGroup) v;
        refreshLayout.setLayoutParams(new ViewGroup.LayoutParams(params.getRefreshViewParams()[0], params.getRefreshViewParams()[1]));
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    public RefreshUtil addView(ListView listView, SwipeRefreshLayout.OnRefreshListener listener){
        parent.removeView(listView);
        refreshLayout.addView(listView);
        refreshLayout.setOnRefreshListener(listener);
        parent.addView(refreshLayout, params.getRefreshViewParams()[2]);
        return this;
    }

    public RefreshUtil addView(RecyclerView listView, SwipeRefreshLayout.OnRefreshListener listener){
        parent.removeView(listView);
        refreshLayout.addView(listView);
        refreshLayout.setOnRefreshListener(listener);
        parent.addView(refreshLayout, params.getRefreshViewParams()[2]);
        return this;
    }

    public SwipeRefreshLayout getRefreshLayout(){
        return refreshLayout;
    }


    /**
     * 宽高与在父布局中的索引
     */
    public interface getViewParams{
        int[] getRefreshViewParams();
    }

}
