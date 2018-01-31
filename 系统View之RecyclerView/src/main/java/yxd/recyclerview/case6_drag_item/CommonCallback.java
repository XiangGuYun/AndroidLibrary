package yxd.recyclerview.case6_drag_item;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 2018/1/7.
 */

public class CommonCallback extends ItemTouchHelper.Callback {


    private List<?> datas;
    private RecyclerView.Adapter adapter;

    public CommonCallback(List<?> datas, RecyclerView.Adapter adapter) {
        this.datas = datas;
        this.adapter = adapter;
    }

    /*
            ①：判断当前是否在滑动
            ②：设置不同布局下拖拽方向
             */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //判断RV的布局管理器是否是网格管理器
        if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            //若是，则拖拽FLAG为上下左右。
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            //若是线性布局，则拖拽FLAG为上下
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    /*
    拖拽时不断调用的方法
    在这里我们需要将正在拖拽的item和集合的item进行交换元素，然后在通知适配器更新数据
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //得到当拖拽的viewHolder的Position
        int fromPosition = viewHolder.getAdapterPosition();
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    /*
    onSwiped（）是替换后调用的方法，可以不用管。
    我们希望在拖拽的时候将被拖拽的Item高亮，这样用户体验要好很多，
    所以我们要重写CallBack对象中的onSelectedChanged()和clearView()方法，
    在选中的时候设置高亮背景色，在完成的时候移除高亮背景色
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    /**
     * 长按选中Item的时候开始调用
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //判断当前的状态为不空闲状态
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            //改变Item视图的背景色
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
        }
        //在调用父类方法之前调用
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 手指松开的时候还原
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        //重新设置背景色
        viewHolder.itemView.setBackgroundColor(0);
    }
}
