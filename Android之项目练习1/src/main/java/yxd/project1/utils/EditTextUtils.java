package yxd.project1.utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;

/**
 * Created by asus on 2018/1/4.
 */

public class EditTextUtils {

    /*
   显示错误提示，并获取焦点
    */
    public static void showError(TextInputLayout til, String error){
        til.setError(error);
        til.getEditText().setFocusable(true);
        til.getEditText().setFocusableInTouchMode(true);
        til.getEditText().requestFocus();
    }

    /*
    验证用户名
     */
    public static boolean verifyUsername(TextInputLayout til, String account){
        if(TextUtils.isEmpty(account)){
            showError(til,"用户名不能为空");
            return false;
        }
        return true;
    }

    /*
    验证密码
     */
    public static boolean verifyPassword(TextInputLayout til, String password) {
        if (TextUtils.isEmpty(password)) {
            showError(til,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(til,"密码长度为6-18位");
            return false;
        }

        return true;
    }

}
