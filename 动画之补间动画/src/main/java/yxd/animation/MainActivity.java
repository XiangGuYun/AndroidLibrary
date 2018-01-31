package yxd.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;
/*
补间动画分为四种类型：透明度、旋转、唯一、缩放，后缀结尾Animation。
还有一个AnimationSet来组织多个或同步流程的动画。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alpha(View view) {
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        view.startAnimation(aa);
    }

    public void rotate(View view) {
        RotateAnimation ra = new RotateAnimation(0, 360,
                view.getPivotX(), view.getPivotY());
        ra.setDuration(1000);
        view.startAnimation(ra);
    }

    public void translate(View view) {
        TranslateAnimation ta = new TranslateAnimation(0, 100,
                0, 0);
        ta.setDuration(1000);
        view.startAnimation(ta);
    }

    public void scale(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 2, 0, 2);
        sa.setDuration(1000);
        view.startAnimation(sa);
    }

    public void set(View view) {
        AnimationSet as = new AnimationSet(true);
        as.setDuration(1000);
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(1000);
        as.addAnimation(aa);
        TranslateAnimation ta = new TranslateAnimation
                (0, 100, 0, 200);
        as.addAnimation(ta);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        view.startAnimation(as);
    }
}
