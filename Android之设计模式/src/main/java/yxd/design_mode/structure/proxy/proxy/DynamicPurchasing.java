package yxd.design_mode.structure.proxy.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * Created by asus on 2017/12/17.
 */
/*
动态代理则是在代码运行时通过反射来动态的生成代理类的对象，并确定到底来代理谁。也就是我们在编码阶段不需要知道代理谁，
代理谁我们将会在代码运行时决定。Java提供了动态的代理接口InvocationHandler，实现该接口需要重写invoke()方法
 */
public class DynamicPurchasing implements InvocationHandler{

    private Object obj;

    public DynamicPurchasing(Object obj){
        this.obj=obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=method.invoke(obj, args);
        return result;
    }
}
