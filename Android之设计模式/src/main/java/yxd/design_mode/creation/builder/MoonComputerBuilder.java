package yxd.design_mode.creation.builder;

/**
 * Created by asus on 2017/12/19.
 */
/*
商家实现了抽象的Builder类，MoonComputerBuilder类用于组装电脑
 */
public class MoonComputerBuilder extends Builder {
    private Computer mComputer = new Computer();
    @Override
    public void buildCpu(String cpu) {
        mComputer.setmCpu(cpu);
    }
    @Override
    public void buildMainboard(String mainboard) {
        mComputer.setmMainboard(mainboard);
    }
    @Override
    public void buildRam(String ram) {
        mComputer.setmRam(ram);
    }
    @Override
    public Computer create() {
        return mComputer;
    }
}
