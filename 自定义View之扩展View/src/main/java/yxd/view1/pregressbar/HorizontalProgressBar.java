package yxd.view1.pregressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import yxd.view1.R;

/**
 * Created by asus on 2017/12/20.
 */

public class HorizontalProgressBar extends ProgressBar{

    private static final int DEFAULT_TEXT_SIZE = 10;//sp
    private static final int DEFAULT_TEXT_COLOR = 0xFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACH = 0xFFD3d6DA;
    private static final int DEFAULT_HEIGHT_UNREACH = 2;//dp
    private static final int DEFAULT_COLOR_REACH = 0xFFFC00D1;
    private static final int DEFAULT_HEIGHT_REACH = 2;//dp
    private static final int DEFAULT_TEXT_OFFSET = 10;//dp
    //文本字体大小
    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    //文本颜色
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    //未到达的颜色
    protected int mUnReachColor = DEFAULT_COLOR_UNREACH;
    //到达的颜色
    protected int mReachColor = DEFAULT_COLOR_REACH;
    //未到达的高度
    protected int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    //到达的高度
    protected int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    //文本偏移量
    protected int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected Paint mPaint = new Paint();//画笔
    protected int mRealWidth;//当前控件的宽度减去Padding值

    public HorizontalProgressBar(Context context) {
        this(context, null);//调用第二个构造函数
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);//调用第三个构造函数
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(attrs);//获取自定义属性
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();//保存新建图层
        //做坐标系原点移动到左边框正中间的位置
        canvas.translate(getPaddingLeft(), getHeight()/2);
        boolean isNeed = false;//是否需要绘制未达到的部分(默认不需要)

        String text = getProgress()+"%";
        float radio = getProgress()*1.0f/getMax();//进度比例
        int textWidth = (int) mPaint.measureText(text);//测量文本宽度
        float pregressX = radio*mRealWidth-mTextOffset/2;
        if(pregressX+textWidth>mRealWidth){
            pregressX=mRealWidth-textWidth;
            isNeed=true;
        }
        float endX = pregressX-mTextOffset/2;
        if(endX>0){
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0,0,endX,0,mPaint);
        }
        //绘制文本
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent()+mPaint.ascent())/2);//测量高度
        canvas.drawText(text, pregressX, y, mPaint);
        //绘制未达到的部分
        if(!isNeed){
            float start=pregressX+mTextSize/2+textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }
        canvas.restore();//合并图层
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
        获取宽度的测量模式和测量大小
         */
        //
        // int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        //测量实际高度
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthSize, height);

        mRealWidth = getMeasuredWidth()-getPaddingLeft()-getPaddingRight();
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode==MeasureSpec.EXACTLY){//精确值
            result = heightSize;
        }else{
            //获取字体高度
            int textHeight = (int) (mPaint.descent()-mPaint.ascent());
            result = getPaddingTop()+getPaddingBottom()
                    +Math.max(Math.max(mReachHeight,mUnReachHeight),Math.abs(textHeight));
            if(heightMode==MeasureSpec.AT_MOST){
                result=Math.min(result, heightSize);
            }
        }
        return result;
    }

    private void obtainStyledAttrs(AttributeSet attrs) {
        //获取属性数组
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressBar);
        mTextSize = (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_text_size, mTextSize);
        mTextColor = ta.getColor(R.styleable.HorizontalProgressBar_progress_text_color, mTextColor);
        mTextOffset = (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_text_offset, mTextOffset);
        mUnReachColor = ta.getColor(R.styleable.HorizontalProgressBar_progress_unreach_color, mUnReachColor);
        mReachColor = ta.getColor(R.styleable.HorizontalProgressBar_progress_reach_color, mReachColor);
        mUnReachHeight = (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_unreach_height, mUnReachHeight);
        mReachHeight = (int) ta.getDimension(R.styleable.HorizontalProgressBar_progress_reach_height, mReachHeight);
        ta.recycle();//回收资源
        mPaint.setTextSize(mTextSize);
    }

    protected int dp2px(int dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
        ,dpVal,getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP
                ,spVal,getResources().getDisplayMetrics());
    }
}
