package yxd.design_mode.behavior.chain_of_responsibility.logger;

/**
 * Created by asus on 2017/12/19.
 */
/*
创建抽象的记录器类
 */
public abstract class AbLogger {

    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;//处理阈值，只有大于或等于该阈值才会进行处理

    //责任链中的下一个元素
    protected AbLogger nextLogger;

    public void setNextLogger(AbLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message){
        if(this.level <= level){
            write(message);
        }
        if(nextLogger !=null){
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);

}
