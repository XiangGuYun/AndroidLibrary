package yxd.scrollview.case1_wrap_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import yxd.scrollview.R;

public class ScrollActivity extends AppCompatActivity {

    private WrapContentListView listView;
    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        for (int i = 0; i < 40; i++) {
            list.add("Item"+(i+1));
        }
        listView = findViewById(R.id.lv);
        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list));

    }
}
