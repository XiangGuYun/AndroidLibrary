package yxd.design_mode.structure.flyweight;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxd.design_mode.R;
/*
运行结果为：
创建商品,key为:iphone7
价格为5199元
使用缓存,key为:iphone7
价格为5199元
使用缓存,key为:iphone7
价格为5999元

从输出看出，只有第一次是创建Goods对象，后面因为key值相同，所以都是使用了对象池中的Goods对象。
在这个例子中，name作为内部状态是不变的，并且作为Map的key值是可以共享的。
而showGoodsPrice方法中需要传入的version值则是外部状态，他的值是变化的。

享元模式的使用场景

系统中存在大量的相似对象。
需要缓冲池的场景。
细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没有特定身份。
 */
public class FlyweightActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyweight);

        Goods goods1=GoodsFactory.getGoods("iphone7");
        goods1.showGoodsPrice("32G");
        Goods goods2=GoodsFactory.getGoods("iphone7");
        goods2.showGoodsPrice("32G");
        Goods goods3=GoodsFactory.getGoods("iphone7");
        goods3.showGoodsPrice("128G");

    }
}
