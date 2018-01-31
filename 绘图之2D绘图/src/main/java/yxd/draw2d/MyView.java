package yxd.draw2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by asus on 2017/12/10.
 */

public class MyView extends View {

    private Paint paint1, paint2;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint1 = new Paint();
        paint1.setAntiAlias(true);//设置抗锯齿
        paint1.setColor(Color.RED);//设置画笔颜色
        paint1.setARGB(255, 255, 0, 0);//设置画笔的ARGB值
        paint1.setAlpha(255);//设置A值(不透明)
        paint1.setTextSize(30);//设置字体尺寸
        paint1.setStyle(Paint.Style.FILL);//设置画笔风格（实心）
        paint1.setStrokeWidth(10);

        paint2 = new Paint();
        paint2.setAntiAlias(true);//设置抗锯齿
        paint2.setColor(Color.GREEN);//设置画笔颜色
        paint2.setARGB(255, 0, 0, 255);//设置画笔的ARGB值
        paint2.setAlpha(255);//设置A值
        paint2.setTextSize(40);//设置字体尺寸
        paint2.setStyle(Paint.Style.STROKE);//设置画笔风格（空心）
        paint2.setStrokeWidth(6);//设置空心边框的宽度
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制点
        canvas.drawPoint(getWidth()/2, getHeight()/2, paint1);
        //绘制直线
        canvas.drawLine(0, 0, 100, 100, paint1);
        //绘制多条直线
        float[] points = {
          100, 100, 200, 0,
          200, 0, 300, 100
        };
        canvas.drawLines(points, paint2);
        //绘制矩形
        canvas.drawRect(100, 100, 200, 200, paint1);
        //绘制圆角矩形
        canvas.drawRoundRect(300, 100, 400, 200, 20, 20, paint2);
        //绘制圆
        canvas.drawCircle(100, 300, 50, paint1);
        //绘制弧形
        canvas.drawArc(100, 300, 400, 600,
                0, 90, false, paint2);
        //绘制扇形
        canvas.drawArc(100, 300, 300, 500,
                0, 90, true, paint1);
        //绘制椭圆
        canvas.drawOval(10, 300, 300, 400, paint2);
        //绘制文本
        canvas.drawText("TEXT", 350, 350, paint1);
        //在指定位置绘制文本
        canvas.drawPosText("TEXT", new float[]{100, 100, 130, 130, 160, 160, 190, 190}, paint2);
        //绘制路径
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(300, 200);
        path.lineTo(100, 500);
        path.lineTo(300, 50);
        canvas.drawPath(path, paint2);
    }
}
