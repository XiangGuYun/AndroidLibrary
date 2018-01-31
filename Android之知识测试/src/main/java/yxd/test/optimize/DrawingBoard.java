package yxd.test.optimize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import yxd.test.L;

/**
 * Created by asus on 2018/1/9.
 */

public class DrawingBoard extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder holder;
    private boolean isDrawing;
    private Paint paint;
    private Path path;
    private Canvas canvas;
    private int lastX, lastY;

    public DrawingBoard(Context context) {
        this(context, null);
    }

    public DrawingBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
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
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing=true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing=false;
    }

    @Override
    public void run() {
        while(isDrawing){
            long time1 = System.currentTimeMillis();
            try{
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawPath(path, paint);
            }catch (Exception e){
                L.d(e.getMessage());
            }finally {
                if(canvas != null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }
            long time2 = System.currentTimeMillis();
            if((time2-time1)<100){
                try {
                    Thread.sleep(100-(time2-time1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastX = (int) event.getX();
        lastY = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(lastX, lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
