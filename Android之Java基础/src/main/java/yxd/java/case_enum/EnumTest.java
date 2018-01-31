package yxd.java.case_enum;

/**
 * Created by asus on 2018/1/9.
 */
/*
https://www.cnblogs.com/davidwang456/p/6138717.html
 */
public class EnumTest {

}

enum Color{

    RED(3),BLUE(5),BLACK(8),YELLOW(12),GREEN(28);

    private int colorValue;

    private Color(int cv){
        this.colorValue=cv;
    }

    private int getColorValue(){
        return colorValue;
    }

    private int value(){
        return ordinal()+1;
    }

}