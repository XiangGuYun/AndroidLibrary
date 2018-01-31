package yxd.design_mode.creation.factory;

/**
 * Created by asus on 2017/12/19.
 */

public abstract class ComputerFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
