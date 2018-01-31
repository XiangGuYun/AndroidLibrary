package yxd.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.concurrent.Executors;

/**
 * Created by asus on 2017/12/10.
 */

public class SineWaveView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private Canvas canvas;
    private boolean isDrawing;
    private Paint paint;
    private Path path;
    private int x, y;

    public SineWaveView(Context context) {
        super(context);
        init();
    }

    public SineWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);
        path = new Path();
        x = 0;
        y = getHeight()/2;
        path.moveTo(x, y);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        isDrawing = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isDrawing){
                    try{
                        canvas = holder.lockCanvas();
                        canvas.drawPath(path, paint);
                    }catch (Exception e){

                    }finally {
                        if(canvas != null)
                            holder.unlockCanvasAndPost(canvas);
                    }
                    x +=1;
                    y = (int)(100*Math.sin(x*2*Math.PI/180)+400);
                    path.lineTo(x, y);
                }
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }
}
