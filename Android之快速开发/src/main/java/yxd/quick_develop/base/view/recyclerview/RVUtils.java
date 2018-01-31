package yxd.quick_develop.base.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

import yxd.project1.base.view.recyclerview.divider.DividerItemDecoration;


/**
 * Created by asus on 2017/12/20.
 */

public class RVUtils {

    private RecyclerView rv;
    private Context context;
    private EasyRVAdapter adapter;
    private List<Integer> heights;
    private StaggeredGridLayoutManager gridManager;

    public RVUtils(RecyclerView recyclerView){
        rv = recyclerView;
        context = recyclerView.getContext();
    }

    public RVUtils setRV(RecyclerView recyclerView){
        rv = recyclerView;
        return this;
    }

    public StaggeredGridLayoutManager getGridManager(int columns){
        return new StaggeredGridLayoutManager(columns, StaggeredGridLayoutManager.VERTICAL);
    }

    public RVUtils manager(RecyclerView.LayoutManager manager){
        if(manager == null){
            rv.setLayoutManager(new LinearLayoutManager(context));
        }else {
            rv.setLayoutManager(manager);
        }
        return this;
    }



    public RVUtils animator(RecyclerView.ItemAnimator animator){
        if(animator==null){
            rv.setItemAnimator(new DefaultItemAnimator());
        }else {
            rv.setItemAnimator(animator);
        }
        return this;
    }

    public RVUtils decorate(RecyclerView.ItemDecoration decoration){
        if(decoration == null){
            rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        }else {
            rv.addItemDecoration(decoration);
        }
        return this;
    }

    public RVUtils fixed(boolean b){
        rv.setHasFixedSize(b);
        return this;
    }

    public RVUtils itemHeights(List<Integer> list){
        heights.addAll(list);
        return this;
    }

    public <T> void adapter(List<T> list, onBindData data, SetMultiCellView cellView, int...itemLayoutId){
        adapter = new EasyRVAdapter<T>(context, list, itemLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                try{
                    data.bind(viewHolder, position);
                }catch (Exception e){

                }
            }
            @Override
            public int getLayoutIndex(int layoutIndex, T item) {
                return cellView.setMultiCellView(layoutIndex);
            }
        };
        rv.setAdapter(adapter);
    }

    public EasyRVAdapter getAdapter(){
        return adapter;
    }

    public interface onBindData{
        void bind(EasyRVHolder holder, int pos);

    }

    public interface SetMultiCellView{
        int setMultiCellView(int position);
    }

}
/*
瀑布流Demo
List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("ITEM"+(i+1));
        }
        RVUtils rvUtils = new RVUtils(recyclerView);
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            heights.add((int) (100 + Math.random() * 300));
        }
       rvUtils.manager(new StaggeredGridLayoutManager(4,
               StaggeredGridLayoutManager.VERTICAL))
               //.decorate(new DividerGridItemDecoration(getActivity()))
               .animator(null)
               .adapter(list, (holder, pos) -> {
                   //获取textview的布局参数
                   ViewGroup.LayoutParams lp = holder.getView(R.id.tv_cell).getLayoutParams();
                   //重新设置高度
                   lp.height = heights.get(pos);
                   //重新设置布局
                   holder.setText(R.id.tv_cell, list.get(pos));
                   click(holder.getView(R.id.tv_cell), v -> {
                       toast("点击了第"+(pos+1)+"项");
                   });
                   },(int position) -> 0, R.layout.cell);
 */
/*
 List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("ITEM"+(i+1));
        }
        for (int i = 0; i < 20; i++) {
            myItems.add("ITEM"+(i+1));
        }
        addItems.add("");
        RVUtils rvUtils = new RVUtils(recyclerView);
        rvUtils.manager(null)
                .decorate(null)
                .animator(null)
                .adapter(list, (holder, pos) -> {
                    holder.setText(R.id.tv_cell, list.get(pos));
                    click(holder.getView(R.id.tv_cell), v -> {
                        toast("点击了第"+(pos+1)+"项");
                    });
                }, (int position) -> 0, R.layout.cell, R.layout.footer);
 */
