package yxd.recyclerview.case2_imooc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import yxd.recyclerview.R;

public class ImoocActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imooc);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    private void initData() {
        SparseArray<DataModel> list = new SparseArray<>();
        for (int i = 0; i < 20; i++) {
            int type = ((int)Math.random()*3+1);
        }
    }
}
