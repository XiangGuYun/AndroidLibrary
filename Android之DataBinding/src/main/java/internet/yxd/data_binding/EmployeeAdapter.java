package internet.yxd.data_binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by asus on 2017/12/20.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<BindingViewHolder> {

    private static final int ITEM_VIEW_TYPE_ON = 1;
    private static final int ITEM_VIEW_TYPE_OFF = 2;
    private LayoutInflater inflater;
    private onItemClickListener listener;
    private List<Employe> employes = new ArrayList<>();

    public interface onItemClickListener{
        void onEmployeeClick(Employe employe);
    }


    public EmployeeAdapter(Context ctx){
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getItemViewType(int position) {
        Employe employe = employes.get(position);
        if(employe.isFired()){
            return ITEM_VIEW_TYPE_OFF;
        }
        return ITEM_VIEW_TYPE_ON;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if(viewType == ITEM_VIEW_TYPE_ON){
            binding = DataBindingUtil.inflate(inflater,
                    R.layout.employe_item_unfired, parent, false);
        }else {
            binding = DataBindingUtil.inflate(inflater,
                    R.layout.employe_item_fired, parent, false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Employe employe = employes.get(position);
        holder.getBinding().setVariable(internet.yxd.data_binding.BR.item, employe);
        holder.getBinding().executePendingBindings();//即时刷新
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onEmployeeClick(employe);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return employes.size();
    }

    Random random = new Random(System.currentTimeMillis());

    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public void addAll(List<Employe> list){
        employes.addAll(list);
    }

    public void add(Employe employe){
        int pos = random.nextInt(employes.size()+1);
        employes.add(employe);
        notifyItemInserted(pos);
    }

    public void remove(Employe employe){
        int pos = random.nextInt(employes.size()+1);
        employes.remove(employe);
        notifyItemRemoved(pos);
    }
}
