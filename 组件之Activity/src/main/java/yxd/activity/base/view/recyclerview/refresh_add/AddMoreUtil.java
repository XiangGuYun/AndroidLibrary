package yxd.activity.base.view.recyclerview.refresh_add;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import rx.Subscriber;
import yxd.activity.base.thread.RxJava;

/**
 * Created by asus on 2016/7/28.
 */
public class AddMoreUtil<T> {

    public static boolean addedFooterView = false;
    public static boolean needShowFoot = false;
    private View addView;

    public static boolean isTimeToShowFoot(List list, int pos){
        return needShowFoot==true&&pos==list.size()-1;
    }


    private RecyclerView rv;
    private List<T> list;
    private Context context;

    public AddMoreUtil(RecyclerView recyclerView, List<T> list){
        rv = recyclerView;
        this.list = list;
        context = rv.getContext();
    }

    public AddMoreUtil setAddView(int layoutId){
        addView = LayoutInflater.from(context).inflate(layoutId, null);
        return this;
    }

    public void addMoreList(UpdateListener listener, TimeConsumingListener timeListener){
        LinearLayoutManager lm= (LinearLayoutManager) rv.getLayoutManager();
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isScrollToBottom = ((RecyclerView.ViewHolder)lm.
                        findViewByPosition(lm.findLastCompletelyVisibleItemPosition()).getTag())
                        .getPosition()+1 == rv.getAdapter().getItemCount()-1;
                if(isScrollToBottom){
                    if(!addedFooterView) {
                        addedFooterView = true;
                        needShowFoot = true;
                        list.add((T) new Object());
                        rv.getAdapter().notifyDataSetChanged();
                        RxJava.thread(msg -> {
                            list.remove(list.size() - 1);
                            rv.getAdapter().notifyItemRemoved(list.size() - 1);
                            listener.update(msg);
                            rv.getAdapter().notifyDataSetChanged();
                            addedFooterView = false;
                            needShowFoot = false;
                        }, (Subscriber sub) -> {
                            timeListener.time(sub);
                        });
                    }
                }
            }
        });
    }


    public void addMoreGrid(UpdateListener listener, TimeConsumingListener timeListener){
        GridLayoutManager lm = (GridLayoutManager) rv.getLayoutManager();
        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isScrollToBottom = ((RecyclerView.ViewHolder)lm.findViewByPosition(lm.findLastCompletelyVisibleItemPosition()).getTag()).getPosition()+1 == rv.getAdapter().getItemCount()-1;
                if(isScrollToBottom){
                    if(!addedFooterView) {
                        addedFooterView = true;
                        needShowFoot = true;
                        //list.addAll(new ArrayList<T>(1));
                        list.add((T) new Object());
                        rv.getAdapter().notifyDataSetChanged();
                        RxJava.thread(msg -> {
                            list.remove(list.size() - 1);
                            listener.update(msg);
                            rv.getAdapter().notifyDataSetChanged();
                            addedFooterView = false;
                            needShowFoot = false;
                        }, timeListener::time);
                    }
                }
            }
        });
    }


    public interface UpdateListener{
        void update(Message msg);
    }

    public interface TimeConsumingListener{
        void time(Subscriber sub);
    }

}
