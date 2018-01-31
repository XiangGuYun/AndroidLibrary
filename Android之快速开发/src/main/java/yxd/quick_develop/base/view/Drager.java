package yxd.quick_develop.base.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by asus on 2016/7/19.
 */
public abstract class Drager extends FrameLayout {
    protected ViewDragHelper mViewDragHelper;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        onFinishInflate_();
    }

    protected abstract void onFinishInflate_();


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        onSizeChanged_(w, h, oldw, oldh);
    }

    protected abstract void onSizeChanged_(int w, int h, int oldw, int oldh);


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper,此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private void initView() {

        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    private ViewDragHelper.Callback callback =
            new ViewDragHelper.Callback() {

                // 何时开始检测触摸事件
                @Override
                public boolean tryCaptureView(View child, int pointerId) {
                    //如果当前触摸的child是mMainView时开始检测
                    return setAffectedSonView(child, pointerId);
                }

                // 触摸到View后回调
                @Override
                public void onViewCaptured(View capturedChild,
                                           int activePointerId) {
                    super.onViewCaptured(capturedChild, activePointerId);
                }

                // 当拖拽状态改变，比如idle，dragging
                @Override
                public void onViewDragStateChanged(int state) {
                    super.onViewDragStateChanged(state);
                }

                // 当位置改变的时候调用,常用与滑动时更改scale等
                @Override
                public void onViewPositionChanged(View changedView,
                                                  int left, int top, int dx, int dy) {
                    super.onViewPositionChanged(changedView, left, top, dx, dy);
                }

                // 处理垂直滑动
                @Override
                public int clampViewPositionVertical(View child, int top, int dy) {
                    return dealVerticalScroll(child, top, dy);
                }

                // 处理水平滑动
                @Override
                public int clampViewPositionHorizontal(View child, int left, int dx) {
                    return dealHorizScroll(child, left, dx);
                }

                // 拖动结束后调用
                @Override
                public void onViewReleased(View releasedChild, float xvel, float yvel) {
                    super.onViewReleased(releasedChild, xvel, yvel);
                    //手指抬起后缓慢移动到指定位置
                    onDragEnd(releasedChild, xvel, yvel);
                }
            };

    protected abstract void onDragEnd(View releasedChild, float xvel, float yvel);

    protected abstract int dealHorizScroll(View child, int left, int dx);

    protected abstract int dealVerticalScroll(View child, int top, int dy);

    protected abstract boolean setAffectedSonView(View child, int pointerId);

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
    public Drager(Context context) {
        super(context);
        initView();
    }

    public Drager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void smoothSlideViewTo(View drag, int finalLeft, int finalTop){
        mViewDragHelper.smoothSlideViewTo(drag, finalLeft, finalTop);
        ViewCompat.postInvalidateOnAnimation(this);
    }



}
