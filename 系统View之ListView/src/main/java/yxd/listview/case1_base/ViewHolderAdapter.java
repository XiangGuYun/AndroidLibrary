package yxd.listview.case1_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yxd.listview.R;

/**
 * Created by asus on 2017/12/11.
 */

public class ViewHolderAdapter extends BaseAdapter {

    private List<String> data;
    private LayoutInflater inflater;

    public ViewHolderAdapter(Context ctx, List<String> data){
        this.data = data;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item, null);
            viewHolder.iv= convertView.findViewById(R.id.iv_cell);
            viewHolder.tv= convertView.findViewById(R.id.tv_cell);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setBackgroundResource(R.mipmap.ic_launcher);
        viewHolder.tv.setText(data.get(position));
        return convertView;
    }

    class ViewHolder{
        public ImageView iv;
        public TextView tv;
    }
}
