package yxd.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import yxd.activity.base.view.Drager;

/**
 * Created by asus on 2016/7/19.
 */
/*
 <yxd.activity.DragView
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#ff0000"
            android:text="菜单"
            android:textSize="40sp"
            android:gravity="center|left"
            />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="#00ff00"
            android:text="MainView"
            android:textSize="40sp"
            android:gravity="center"
            />

    </yxd.activity.DragView>
 */
public class DragView extends Drager {

    private View mMenuView, mMainView;
    private int mWidth;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 当完成容器渲染后获取子控件
     */
    @Override
    protected void onFinishInflate_() {
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onSizeChanged_(int w, int h, int oldw, int oldh) {
        mWidth = mMenuView.getMeasuredWidth();
    }

    /**
     * 当拖拽结束后如何处理受拖拽影响的子View
     */
    @Override
    protected void onDragEnd(View releasedChild, float xvel, float yvel) {
        if (mMainView.getLeft() < 500) {//关闭菜单
            smoothSlideViewTo(mMainView, 0, 0); //相当于Scroller的startScroll方法
        } else {//打开菜单
            smoothSlideViewTo(mMainView, 500, 0);
        }
    }

    /**
     * 处理横向拖动
     */
    @Override
    protected int dealHorizScroll(View child, int left, int dx) {
        return left;
    }

    /**
     * 处理纵向拖动
     */
    @Override
    protected int dealVerticalScroll(View child, int top, int dy) {
        return 0;
    }

    /**
     * 设置受拖拽影响的子View
     */
    @Override
    protected boolean setAffectedSonView(View child, int pointerId) {
        return mMainView == child;
    }


}
