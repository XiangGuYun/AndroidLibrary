package yxd.activity.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/*
    请问对话框的背景是一张图片，还是通过shape来设置的？
    还有一个方法可以把圆角边边的黑色菱角去掉，在自定义的文件里加上：
    getWindow().setBackgroundDrawable(new BitmapDrawable());
    或者getWindow().setBackgroundDrawableResource(android.R.color.transparent);
 */

/**
 * Created by asus on 2016/7/26.
 */
public abstract class ModelDialog extends Dialog implements View.OnClickListener{

    protected Context context;

    public ModelDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        init();
    }

    private void init() {
        setContentView(LayoutInflater.from(context).inflate(getDialogLaout(), null));
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics();// 获取屏幕宽、高用
        init(dialogWindow, lp, d);
//        dialogWindow.setBackgroundDrawableResource(R.drawable.round_dialog);
//        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
//        lp.height = (int) (d.heightPixels*0.3 );
//        dialogWindow.setAttributes(lp);
    }

    protected void init(Window dialogWindow, WindowManager.LayoutParams lp, DisplayMetrics d) {
    }

    protected void setWindow(Window dialogWindow, WindowManager.LayoutParams lp, DisplayMetrics d) {
    }

    protected abstract int getDialogLaout();


}
