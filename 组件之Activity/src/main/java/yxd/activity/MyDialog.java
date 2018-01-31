package yxd.activity;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import yxd.activity.base.dialog.ModelDialog;

/**
 * Created by asus on 2017/12/21.
 */

public class MyDialog extends ModelDialog {

    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void init(Window dialogWindow, WindowManager.LayoutParams lp, DisplayMetrics d) {
        super.init(dialogWindow, lp, d);
        dialogWindow.setBackgroundDrawableResource(R.drawable.round_dialog);
        lp.width = (int) (d.widthPixels*(0.8));
        lp.height = (int)(d.heightPixels*0.3);
        dialogWindow.setAttributes(lp);
    }

    @Override
    protected int getDialogLaout() {
        return R.layout.dialog;
    }

    @Override
    public void onClick(View v) {

    }
}
