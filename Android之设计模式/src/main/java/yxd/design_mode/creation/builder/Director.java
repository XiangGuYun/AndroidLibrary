package yxd.design_mode.creation.builder;

/**
 * Created by asus on 2017/12/19.
 */
/*
用Dirextor指挥者类来统一组装过程

商家的指挥者类用来规范组装电脑的流程规范，先安装主板，再安装CPU，最后安装内存并组装成电脑
 */
public class Director {
    Builder mBuild=null;
    public Director(Builder build){
        this.mBuild=build;
    }
    public Computer CreateComputer(String cpu,String mainboard,String ram){
        //规范建造流程
        this.mBuild.buildMainboard(mainboard);
        this.mBuild.buildCpu(cpu);
        this.mBuild.buildRam(ram);
        return mBuild.create();
    }
}
