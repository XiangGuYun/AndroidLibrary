package yxd.design_mode.structure.flyweight;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Log;

/**
 * Created by asus on 2017/12/17.
 */
/*
享元工厂GoodsFactory 用来创建Goods对象。通过Map容器来存储Goods对象，将内部状态name作为Map的key，
以便标识Goods对象。如果Map容器中包含此key，则使用Map容器中存储的Goods对象，
否则就新创建Goods对象，并放入Map容器中。
 */
@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class GoodsFactory {

    private static ArrayMap<String,Goods> pool=new ArrayMap<>();

    public static Goods getGoods(String name){
        if(pool.containsKey(name)){
            Log.d("Test", "使用缓存,key为:"+name);
            return pool.get(name);
        }else{
            Goods goods=new Goods(name);
            pool.put(name,goods);
            Log.d("Test", "创建商品,key为:"+name);
            return goods;
        }
    }
}
