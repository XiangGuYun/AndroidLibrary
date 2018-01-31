package internet.yxd.data_binding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import internet.yxd.data_binding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 3000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        User user = new User("ZhaoYun", "ZiLong");
        binding.setUser(user);
    }


    //binding.tv.setText("爱情故事");
}
