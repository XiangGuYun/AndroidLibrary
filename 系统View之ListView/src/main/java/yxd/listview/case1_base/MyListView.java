package yxd.listview.case1_base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by asus on 2017/12/11.
 */

public class MyListView extends ListView {

    private int mMaxOverDistance = 30;

    public MyListView(Context context) {
        super(context);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        /*
        根据不同的屏幕分辨率来修改距离值
         */
        DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) (density*mMaxOverDistance);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {


        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
}
