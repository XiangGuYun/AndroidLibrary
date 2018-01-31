package internet.yxd.data_binding;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import internet.yxd.data_binding.databinding.ActivityListBinding;

public class ListActivity extends AppCompatActivity {

    ActivityListBinding binding;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        binding.setPresentor(new Presentor());

        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EmployeeAdapter(this);
        binding.rv.setAdapter(adapter);
        adapter.setListener(new EmployeeAdapter.onItemClickListener() {
            @Override
            public void onEmployeeClick(Employe employe) {
                Toast.makeText(ListActivity.this, employe.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        List<Employe> employes = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if(i%2==0){
                employes.add(new Employe(true, "FirstName"+i,
                        "LastName"+i));
            }else {
                employes.add(new Employe(false, "FirstName"+i,
                        "LastName"+i));
            }
        }
        adapter.addAll(employes);

    }

    public class Presentor{
        public void onClickAddItem(View view){
            adapter.add(new Employe(true, "Xin", "YuanGong"));
        }
        public void onClickRemoveItem(View view){
            adapter.remove(new Employe(false, "Xin", "YuanGong"));
        }
    }
}
