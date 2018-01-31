package yxd.design_mode.creation.factory;

/**
 * Created by asus on 2017/12/19.
 */

/**
 * 广达代工厂
 */
public class GDComputerFactor extends ComputerFactory {
    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer=null;
        String classname=clz.getName();
        try {
            //通过反射来生产不同厂家的电脑
            computer= (Computer) Class.forName(classname).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
