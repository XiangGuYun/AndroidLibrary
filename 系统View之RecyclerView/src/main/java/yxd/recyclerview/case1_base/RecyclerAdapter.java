package yxd.recyclerview.case1_base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yxd.recyclerview.R;

/**
 * Created by asus on 2017/12/11.
 */

public class RecyclerAdapter extends RecyclerView.Adapter
        <RecyclerAdapter.ViewHolder> {

    private List<String> data;
    private OnItemClickListener onItemClickListener;

    public RecyclerAdapter(List<String> data){
        this.data = data;
    }

    public void removeData(int pos){
        data.remove(pos);
        notifyItemRemoved(pos);
    }

    /*
    创建ViewHolder并返回
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    /*
    设置每个Item绑定的数据
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position));
        if(onItemClickListener != null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(v, position, holder);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /*
    ViewHolder继承RV的ViewHolder并实现方法来初始化Item里面的子View
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_cell);
        }
    }

    /*
   自定义点击事件
   列表中条目的点击事件需要我们自己来定义，这是一个不尽如人意的地方，
   但是自定义点击事件的话也并不是很难，在adaper中定义接口并提供回调
    */
    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position,ViewHolder vh);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        onItemClickListener = mOnItemClickListener;
    }

}
