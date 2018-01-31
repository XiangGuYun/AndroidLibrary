package yxd.project1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import yxd.project1.R;
import yxd.project1.base.common.L;
import yxd.project1.base.context.BaseFragment;

/**
 * Created by asus on 2018/1/3.
 */

@SuppressLint("AppCompatCustomView")
public class DeleteTextView extends TextView {

    private Bitmap bitmap;

    public static boolean needBitmap = false;

    public DeleteTextView(Context context) {
        super(context);
        init();
    }

    public DeleteTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeleteTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.delete);
    }

    public void chonghui(){
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(needBitmap){
            canvas.drawBitmap(bitmap, getWidth()-bitmap.getWidth(), 0, null);
        }
    }
}
