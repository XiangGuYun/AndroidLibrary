package yxd.animator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /*
    给添加新的子视图增加默认的动画效果
     */
    public void add1(View view) {
        Button btn = new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(0, 20, 0, 0);
        btn.setLayoutParams(lp);
        btn.setText("NewBtn");
        btn.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout ll = (LinearLayout) view.getParent();
        ll.addView(btn);
    }

    /*
   给添加新的子视图增加自定义的动画效果
    */
    public void add2(View view) {
        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1);
        sa.setDuration(2000);
        LayoutAnimationController lac =
                new LayoutAnimationController(sa, 0.5f);//延迟0.5秒
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        LinearLayout ll = (LinearLayout) view.getParent();
        ll.setLayoutAnimation(lac);

        Button btn = new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(0, 20, 0, 0);
        btn.setLayoutParams(lp);
        btn.setText("NewBtn");
        btn.setGravity(Gravity.CENTER_HORIZONTAL);

        ll.addView(btn);
    }

}
