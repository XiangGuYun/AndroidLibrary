package yxd.recyclerview.case1_base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yxd.recyclerview.R;

/**
 * Created by asus on 2017/12/11.
 */

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.ViewHolder>{

    List<String> list;
    List<Integer> heights;//设置每个Item的高度

    public WaterFallAdapter(List<String> list){
        this.list = list;
        heights = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            heights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //获取textview的布局参数
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        //重新设置高度
        lp.height = heights.get(position);
        //重新设置布局
        holder.tv.setLayoutParams(lp);
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_cell);
        }
    }
}
