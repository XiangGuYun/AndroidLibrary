package yxd.design_mode.creation.prototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import yxd.design_mode.R;
/*
原型模式的使用场景

如果类的初始化需要耗费较多的资源，那么可以通过原型拷贝避免这些消耗。
通过new产生一个对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。
一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时，可以拷贝多个对象供调用者使用，即保护性拷贝。

原型模式的优缺点

优点

原型模式是在内存中二进制流的拷贝，要比new一个对象的性能要好，特别是需要产生大量对象时。

缺点

直接在内存中拷贝，构造函数是不会执行的，这样就减少了约束，这既是优点也是缺点，需要在实际应用中去考量。
 */
public class ProtoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proto_activity);

    }

    public void shallow_copy(View view) {
        BusinessCard businessCard = new BusinessCard();
        //拷贝名片
        BusinessCard cloneCard1 = null;
        BusinessCard cloneCard2 = null;
        try {
            businessCard.setName("钱三");
            businessCard.setCompany("阿里");
            cloneCard1 = businessCard.clone();//不会触发构造函数
            cloneCard1.setName("赵四");
            cloneCard1.setCompany("百度");
            cloneCard2 = businessCard.clone();//不会触发构造函数
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        cloneCard2.setName("孙五");
        cloneCard2.setCompany("腾讯");
        businessCard.show();
        cloneCard1.show();
        cloneCard2.show();
    }

    public void deep_copy(View view) {
        DeepBusinessCard businessCard=new DeepBusinessCard();
        businessCard.setName("钱三");
        businessCard.setCompany("阿里","北京望京");
        DeepBusinessCard cloneCard1=businessCard.clone();
        cloneCard1.setName("赵四");
        cloneCard1.setCompany("百度","北京西二旗");
        DeepBusinessCard cloneCard2=businessCard.clone();
        cloneCard2.setName("孙五");
        cloneCard2.setCompany("腾讯","北京中关村");
        businessCard.show();
        cloneCard1.show();
        cloneCard2.show();
    }
}
