package yxd.spfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FileUtil fileUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileUtil = new FileUtil(this);

    }

    public void btn1(View view) {
         /*
        默认路径是内置存储卡的/data/data/包名/files/目录下
         */
        fileUtil.save("Hello World!", "data.txt");
    }


    public void btn2(View view) {
        Toast.makeText(this,
                fileUtil.load("data.txt"), Toast.LENGTH_SHORT).show();
    }

    public void btn3(View view) {
        SPUtils.setParam(this, "key", "Hello SP!");
    }

    public void btn4(View view) {
        String str = (String) SPUtils.getParam(this, "key", "");
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
