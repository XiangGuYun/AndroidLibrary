package yxd.listview.case2_divide_page_load;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yxd.listview.R;

/**
 * Created by asus on 2018/1/9.
 */

public class ListViewAdapter extends BaseAdapter {

    private static Map<Integer,View> m=new HashMap<Integer,View>();

    private List<String> items;
    private LayoutInflater inflater;

    public ListViewAdapter(List<String> items, Context context) {
        super();
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        contentView=m.get(position);
        if(contentView==null){
            contentView=inflater.inflate(R.layout.item_case2, null);
            TextView text= contentView.findViewById(R.id.list_item_text);
            text.setText(items.get(position));
        }
        m.put(position, contentView);
        return contentView;
    }

    public void addItem(String item) {
        items.add(item);
    }

}
