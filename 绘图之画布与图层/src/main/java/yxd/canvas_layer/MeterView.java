package yxd.canvas_layer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by asus on 2017/12/10.
 */

public class MeterView extends View {

    private Paint paint;
    private int radius;

    public MeterView(Context context) {
        super(context);
        init();
    }

    public MeterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();//保存并新建图层
        if(getWidth()>getHeight()){
            radius = getHeight()/2;
        }else {
            radius = getWidth()/2;
        }
        canvas.translate(radius, radius);
        canvas.drawCircle(0, 0, radius, paint);
        canvas.drawPoint(0, 0, paint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(0, -radius*9/10, 0, -radius, paint);
            if(i==0){
                canvas.drawText(12+"", 0, -radius*8/10, paint);
            }else {
                canvas.drawText(i+"", 0, -radius*8/10, paint);
            }
            canvas.rotate(30);
        }
        canvas.restore();//合并图层


    }
}
