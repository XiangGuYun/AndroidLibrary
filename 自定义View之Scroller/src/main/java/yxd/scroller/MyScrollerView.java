package yxd.scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by asus on 2017/12/14.
 */

public class MyScrollerView extends View {

    private Scroller scroller;
    private int lastX, lastY;
    private int offsetX, offsetY;

    public MyScrollerView(Context context) {
        super(context);
        init(context);
    }

    public MyScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    /*
    在构造方法中初始化Scroller对象
     */
    private void init(Context context) {
        scroller = new Scroller(context);
    }

    /*
    重写View的computeScroll()方法
     */
    @Override
    public void computeScroll() {//计算滑动
        super.computeScroll();
        if(scroller.computeScrollOffset()){//判断是否完成整个滑动
            ((View)getParent()).scrollTo(
                    scroller.getCurrX(),//获取当前的滑动坐标
                    scroller.getCurrY());
            //通过重绘来不断地调用computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //当手指按下时记录最初的坐标
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //当手指移动时根据当前坐标计算出偏移量
                offsetX = x - lastX;
                offsetY = y - lastY;
                //利用父容器的scrollBy不断地移动，注意偏移量要取反
                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，执行滑动过程
                View viewGroup = (View) getParent();
                /*
                getScrollX():获取子View在X轴的偏移量，传入正数会向左移，
                getScrollY():获取子View在Y轴的偏移量，传入正数会向上移，
                 */
                scroller.startScroll(
                        viewGroup.getScrollX(),//view
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),//将偏移量设置为相反数
                        -viewGroup.getScrollY()
                );
                invalidate();
                break;

        }
        return true;
    }
}
