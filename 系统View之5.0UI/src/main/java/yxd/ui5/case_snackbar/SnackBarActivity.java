package yxd.ui5.case_snackbar;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import yxd.ui5.R;

/*
make : 构造一个Snackbar对象。可指定提示条的上级视图、提示消息文本、显示时长等信息。
setText : 设置提示消息的文本内容。
setAction : 设置交互按钮的文本与点击监听器。
setActionTextColor : 设置交互按钮的文本颜色。
setDuration : 设置提示消息的显示时长。
show : 显示提示条。
 */
public class SnackBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack_bar);

    }

    public void btn(View view){
        /*
        Snackbar.LENGTH_SHORT// 短时间显示，然后自动取消
        Snackbar.LENGTH_LONG// 长时间显示，然后自动取消
        Snackbar.LENGTH_INDEFINITE// 不消失显示，除非手动取消
         */
        Snackbar snackBar =
                Snackbar.make(view,"it is snackbar!",Snackbar.LENGTH_SHORT);
        //设置SnackBar背景颜色
        snackBar.getView().setBackgroundResource(R.color.bg_snackbar);
        //设置按钮文字颜色
        snackBar.setActionTextColor(Color.WHITE);
        snackBar.setAction("Click", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SnackBarActivity.this,
                        "这是一个点击事件",
                        Toast.LENGTH_SHORT).show();
            }
        });
        snackBar.setCallback(new Snackbar.Callback(){
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Toast.makeText(SnackBarActivity.this,
                        "Snackbar dismiss",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar sb) {
                super.onShown(sb);
                Toast.makeText(SnackBarActivity.this, "Snackbar show",
                        Toast.LENGTH_SHORT).show();
            }
        });
        snackBar.show();
    }
}
