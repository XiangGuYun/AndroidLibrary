package yxd.view2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WheelView wheelView1;
    private String TAG = "TEST";
    private WheelView wheelView2;
    private WheelView wheelView3;
    private TextView tvYear, tvMonth, tvDay, tvAmPm;
    private SimpleDateFormat sdfYear, sdfMonth, sdfDay;
    private List<String> months;
    private List<String> years = new ArrayList<>();
    private WheelView wheelView;

    private static boolean isRunNian(int year) {
        return ((year%4==0&&year%100!=0)||year%400==0);
    }

    private SimpleDateFormat simpleDateFormat;

    public void addItem(int days){
        wheelView2.views.removeAllViews();
        wheelView2.items.clear();
        wheelView2.items.add("");
        for (int i = 0; i < days; i++) {
            wheelView2.items.add((i+1)+"日");
        }
        wheelView2.items.add("");
        for (String i : wheelView2.items) {
            wheelView2.views.addView(wheelView2.createView(i));
        }
        wheelView2.refreshItemView(wheelView2.getScrollY());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wheelView = findViewById(R.id.wheel);
        wheelView1 = findViewById(R.id.wheel1);
        wheelView2 = findViewById(R.id.wheel2);
        wheelView3 = findViewById(R.id.wheel3);
        sdfYear = new SimpleDateFormat("yyyy");
        sdfMonth = new SimpleDateFormat("MM");
        sdfDay = new SimpleDateFormat("dd");
        tvYear = findViewById(R.id.tvYear);
        tvMonth = findViewById(R.id.tvMonth);
        tvDay = findViewById(R.id.tvDay);
        tvAmPm = findViewById(R.id.tvAmPm);

        tvYear.setText(getYear()+"年");
        tvMonth.setText(getMonth()+"月");
        tvDay.setText(getDay()+"日");
        tvAmPm.setText("上午");

        wheelView.setOffset(1);
        for (int i = 1970; i < 2100; i++) {
            years.add(i+"年");
        }
        wheelView.setItems(years);
        wheelView.setSeletion(years.indexOf(getYear()+"年"));
        wheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
            @Override
            public void onSelected(int selectedIndex, String item) {
                tvYear.setText(item);
            }
        });
        wheelView1.setOffset(1);
        wheelView1.setItems(months=Arrays.asList(
                "1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"
        ));
        wheelView1.setSeletion(Integer.parseInt(getMonth())-1);
        wheelView1.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                switch (item){
                    case "1月"://31
                        tvMonth.setText("01月");
                        addItem(31);
                        break;
                    case "2月"://28
                        tvMonth.setText("02月");
                        addItem(28);
                        break;
                    case "3月"://31
                        tvMonth.setText("03月");
                        addItem(31);
                        break;
                    case "4月"://30
                        tvMonth.setText("04月");
                        addItem(30);
                        break;
                    case "5月"://31
                        tvMonth.setText("05月");
                        addItem(31);
                        break;
                    case "6月"://30
                        tvMonth.setText("06月");
                        addItem(30);
                        break;
                    case "7月"://31
                        tvMonth.setText("07月");
                        addItem(31);
                        break;
                    case "8月"://31
                        tvMonth.setText("08月");
                        addItem(31);
                        break;
                    case "9月"://30
                        tvMonth.setText("09月");
                        addItem(30);
                        break;
                    case "10月"://31
                        tvMonth.setText("10月");
                        addItem(31);
                        break;
                    case "11月"://30
                        tvMonth.setText("11月");
                        addItem(30);
                        break;
                    case "12月"://31
                        tvMonth.setText("12月");
                        addItem(31);
                        break;
                }

            }
        });

        wheelView2.setOffset(1);
        wheelView2.setItems(Arrays.asList("01日","02日","03日","04日","05日","06日","07日",
                "08日","09日","10日","11日","12日","13日","14日","15日","16日","17日","18日","19日",
                "20日","21日","22日","23日","24日","25日","26日","27日","28日","29日","30日","31日"));
        wheelView2.setSeletion(Integer.parseInt(getDay())-1);
        wheelView2.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
                tvDay.setText(item);
            }
        });


        wheelView3.setOffset(1);
        wheelView3.setItems(Arrays.asList("上午","下午"));
        wheelView3.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                Log.d(TAG, "selectedIndex: " + selectedIndex + ", item: " + item);
                tvAmPm.setText(item);
            }
        });
    }

    private String getYear() {
        return sdfYear.format(new Date(System.currentTimeMillis()));
    }
    private String getMonth() {
        return sdfMonth.format(new Date(System.currentTimeMillis()));
    }
    private String getDay() {
        return sdfDay.format(new Date(System.currentTimeMillis()));
    }
}
