package yxd.project1.base.view;

import android.support.design.widget.Snackbar;
import android.view.View;
/*
    fab.setOnClickListener(view -> {
        new SnackBarUtil(view, "你点击了按钮", SnackBarUtil.LONG)
                .show().action("知道了", (view1, snackbar) -> {
                    snackbar.dismiss();
        });
    });
 */

/**
 * Created by asus on 2016/7/26.
 * Toast的升级版
 */
public class SnackBarUtil {

    private Snackbar snackbar;

    public static final int LONG = Snackbar.LENGTH_LONG;
    public static final int SHORT = Snackbar.LENGTH_SHORT;
    public static final int INDEFINITE = Snackbar.LENGTH_INDEFINITE;

    public SnackBarUtil(View target, String msg, int len_s_l_i){
        this.snackbar = Snackbar.make(target, msg, len_s_l_i);
    }

    public SnackBarUtil show(){
        snackbar.show();
        return this;
    }

    public Snackbar getSnackBar(){
        if(snackbar != null){
            return snackbar;
        } else {
            throw new NullPointerException("SnackBar为空!");
        }
    }

    public SnackBarUtil action(String msg, OnClickListener listener){
        snackbar.setAction(msg, view -> {
            listener.onClick(view, snackbar);
        });
        return this;
    }

    public interface OnClickListener{
        void onClick(View view, Snackbar snackbar);
    }




}
