package yxd.test.design_pattern;

import android.util.Log;

/**
 * Created by asus on 2018/1/9.
 */

public class FactoryTest {

    public static void main(){
        Factory factory = new Factory();
        ProductA productA = factory.create(ProductA.class,"产品A");
        ProductB productB = factory.create(ProductB.class,"产品B");
        productA.info();
        productB.info();
    }

}

abstract class Product{

    protected String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public abstract void info();

}

class ProductA extends Product{
    @Override
    public void info() {
        Log.d("Test", "我是"+info);
    }
}

class ProductB extends Product{
    @Override
    public void info() {
        Log.d("Test", "我是"+info);
    }
}


class Factory{

    public <T extends Product> T create(Class<T> aClass, String info){
        Product product = null;
        try {
            product = (Product) Class.forName(aClass.getName()).newInstance();
            product.setInfo(info);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) product;
    }


}
