package internet.yxd.interview.onSaveInstanceState;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Random;

import internet.yxd.interview.R;

public class Case1Activity extends AppCompatActivity {

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        bundle = savedInstanceState;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        int num = (int) ((Math.random())*100);
        outState.putString("save", "保存的随机数是"+num);
        Log.d("Test", "保存的随机数是"+num);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("Test", "恢复后获得的信息是 "+savedInstanceState.getString("save"));
    }

    public void getSave(View view) {
        Log.d("Test", "从Bundle中取出的信息是   "+bundle.getString("save"));
    }
}
