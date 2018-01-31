package yxd.ui5.case_textinput;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import yxd.ui5.R;

/*
http://www.jianshu.com/p/de9c19d73450
 */
public class TextInputActivity extends AppCompatActivity {

    TextInputLayout inputLayout1, inputLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        inputLayout1 = (TextInputLayout) findViewById(R.id.til1);
        inputLayout2 = (TextInputLayout)findViewById(R.id.til2);

    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private void showError(TextInputLayout textInputLayout,String error){
        inputLayout2.setError(error);
        inputLayout2.getEditText().setFocusable(true);
        inputLayout2.getEditText().setFocusableInTouchMode(true);
        inputLayout2.getEditText().requestFocus();
    }

    /**
     * 验证用户名
     * @param account
     * @return
     */
    private boolean validateAccount(String account){
        if(TextUtils.isEmpty(account)){
            showError(inputLayout1,"用户名不能为空");
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     * @param password
     * @return
     */
    private boolean validatePassword(String password) {
        if (TextUtils.isEmpty(password)) {
            showError(inputLayout2,"密码不能为空");
            return false;
        }

        if (password.length() < 6 || password.length() > 18) {
            showError(inputLayout2,"密码长度为6-18位");
            return false;
        }

        return true;
    }

    public void enter(View view) {
        String account = inputLayout1.getEditText().getText().toString();
        String password = inputLayout2.getEditText().getText().toString();

        inputLayout1.setErrorEnabled(true);
        inputLayout2.setErrorEnabled(true);

        //验证用户名和密码
        if(validateAccount(account)&&validatePassword(password)){
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        }
    }
}
