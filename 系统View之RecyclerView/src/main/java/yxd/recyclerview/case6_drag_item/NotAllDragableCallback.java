package yxd.recyclerview.case6_drag_item;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by asus on 2018/1/7.
 */
/*
不允许拖动特定的Item
 */
public class NotAllDragableCallback extends CommonCallback {

    public NotAllDragableCallback(List<?> datas, RecyclerView.Adapter adapter) {
        super(datas, adapter);
    }

    /*
    重写拖拽不可用
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }


}
