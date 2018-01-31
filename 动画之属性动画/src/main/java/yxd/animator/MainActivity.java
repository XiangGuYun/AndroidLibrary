package yxd.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    类动画器的一般用法
     */
    public void translate(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                view,//作用View
                "translationX",//作用属性
                0, 300//作用值（动态数量）
        );
        animator.setDuration(1000);
        animator.start();
    }

    /*
    对没有get和set方法的属性做包装
     */
    public void wrapper(View view) {
        WrapperView wrapperView = new WrapperView(view);
        ObjectAnimator.ofInt(wrapperView, "width", 500)
                .setDuration(5000).start();
    }

    /*
    属性值持有者
    同时做多种属性动画
     */
    public void holder(View view) {
        PropertyValuesHolder pvh1 =
                PropertyValuesHolder.ofFloat("translationX", 300f);
        PropertyValuesHolder pvh2 =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh3 =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvh1, pvh2, pvh3)
                .setDuration(2000).start();
    }

    /*
    ValueAnimator值动画器
     */
    public void value(final View view) {
        //值动画器
        ValueAnimator animator = ValueAnimator.ofFloat(0, 100);
        //作用View
        animator.setTarget(view);
        //设置时长并启动
        animator.setDuration(1000).start();
        //设置更新监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //获取animation的动画值
                Float value = (Float) animation.getAnimatedValue();
                //设置X轴偏移量
                view.setTranslationX(value);
            }
        });
    }

    /*
    属性动画监听器
     */
    public void listener(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "alpha", 0.5f);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //动画开始
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //动画结束
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //动画中断
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //动画重复
            }
        });
        //设置动画监听适配器，只实现需要的方法
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        });
        animator.start();
    }

    /*
    动画集AnimatorSet
    不仅能同时演示动画，也能顺序演示动画
     */
    public void set(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat
                (view, "translationX", 100f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat
                (view, "scaleX", 3f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat
                (view, "scaleY", 3f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }

    /*
    在XML中使用属性动画
     */
    public void xml(View view) {
        Animator anim = AnimatorInflater.loadAnimator(this,
                R.animator.scalex);
        anim.setTarget(view);
        anim.start();
    }

    /*
    3.0后属性动画的一种简写方式
    使用了建造者模式
     */
    public void animate(View view) {
        view.animate()//返回视图属性动画器
                .alpha(0)//透明度变为0
                .y(-100)//Y轴偏移-100像素
                .setDuration(1000)//动画持续1秒
                .withStartAction(new Runnable() {
                    @Override
                    public void run() {
                        //监听动画开始动作
                        Log.d("Test", "start...");
                    }
                })
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        //监听动画结束动作
                        Log.d("Test", "end...");
                    }
                }).start();//开始
    }

    /*
    布局动画
     */
    public void layout(View view) {
       startActivity(new Intent(this, SecondActivity.class));
    }


    static class WrapperView{

        private View target;

        public WrapperView(View target){
            this.target = target;
        }

        public int getWidth(){
            return target.getLayoutParams().width;
        }

        public void setWidth(int width){
            target.getLayoutParams().width = width;
            target.requestLayout();
        }
    }
}
