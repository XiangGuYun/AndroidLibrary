package yxd.view1.pregressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import yxd.view1.R;

/**
 * Created by asus on 2017/12/20.
 */

public class RoundProgressBar extends HorizontalProgressBar {

    private int mRadius = dp2px(30);
    private int mMaxPaintWidth;//最大画笔宽度
    private RectF rectF;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mReachHeight = (int) (mUnReachHeight*2.5f);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        mRadius = (int) ta.getDimension(R.styleable.RoundProgressBar_radius, mRadius);
        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        rectF = new RectF(0,0,mRadius*2,mRadius*2);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress()+"%";
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent()+mPaint.ascent())/2;
        canvas.save();
        canvas.translate(getPaddingLeft()+mMaxPaintWidth/2
                , getPaddingTop()+mMaxPaintWidth/2);//原点为左上角
        mPaint.setStyle(Paint.Style.STROKE);
        //绘制未达到的部分
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
        //绘制达到的部分
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle = getProgress()*1.0f/getMax()*360;
        canvas.drawArc(rectF, 0,sweepAngle,false,mPaint);
        //绘制文本
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTextColor);
        canvas.drawText(text,mRadius-textWidth/2,mRadius-textHeight/2,mPaint);
        canvas.restore();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMaxPaintWidth = Math.max(mReachHeight, mUnReachHeight);
        //默认四个Padding一致
        int expect = mRadius*2+mMaxPaintWidth+getPaddingLeft()+getPaddingRight();

        int width = resolveSize(expect, widthMeasureSpec);
        int height = resolveSize(expect, heightMeasureSpec);

        int readWidth = Math.min(width, height);

        mRadius = (readWidth-getPaddingLeft()-getPaddingRight()-mMaxPaintWidth)/2;
        setMeasuredDimension(readWidth, readWidth);
    }
}
