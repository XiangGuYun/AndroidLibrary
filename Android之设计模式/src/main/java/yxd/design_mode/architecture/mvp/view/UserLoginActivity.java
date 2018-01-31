package yxd.design_mode.architecture.mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import yxd.design_mode.R;
import yxd.design_mode.architecture.mvp.bean.User;
import yxd.design_mode.architecture.mvp.presentor.UserLoginPresenter;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginView{

    EditText et_username, et_password;
    ProgressBar progressBar;

    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et_password = findViewById(R.id.et_password);
        et_username = findViewById(R.id.et_username);
        progressBar = findViewById(R.id.pb);

    }

    public void login(View view) {
        mUserLoginPresenter.login();
    }

    public void cancel(View view) {
        mUserLoginPresenter.clear();
    }

    @Override
    public String getUserName() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void clearUserName() {
        et_username.setText("");
    }

    @Override
    public void clearPassword() {
        et_password.setText("");
    }

    @Override
    public void showLoading() {
       progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getUsername() +
                " login success , to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }
}
