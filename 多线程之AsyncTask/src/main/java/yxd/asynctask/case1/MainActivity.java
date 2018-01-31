package yxd.asynctask.case1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import yxd.asynctask.R;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    private MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
    }

    public void click(View view) {
        myTask = new MyTask(tv);
        myTask.execute("开始吧");
    }

    static class MyTask extends AsyncTask<String, Integer, String> {
        private WeakReference<TextView> tv;

        public MyTask(TextView textView){
            tv = new WeakReference<>(textView);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("Test", "onPreExecute执行了");
            tv.get().setText("准备好了");
        }

        @Override
        protected String doInBackground(String... strings) {
            for (int i = 0; i < 10; i++) {
                if(!isCancelled()){
                    Log.d("Test", "doInBackground"+strings[0]+i);
                    try {
                        Thread.sleep(1000);
                        publishProgress(i+1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "后台任务完成了";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("Test", "onProgressUpdate执行了"+values[0]);
            tv.get().setText(values[0]+"");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Test", "onPostExecute执行了");
            tv.get().setText("执行完毕");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myTask.cancel(true);
    }

}

