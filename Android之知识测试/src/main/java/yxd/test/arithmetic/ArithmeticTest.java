package yxd.test.arithmetic;

import android.util.Log;

/**
 * Created by asus on 2018/1/8.
 */

public class ArithmeticTest {

    static int arr[]={3,1,9,2,5,7,10,4,6,8};

    public static void main(){
        for (int num:bubble(arr)) {
            Log.d("Test", "冒泡排序："+num);
        }
        for (int num:select(arr)) {
            Log.d("Test", "选择排序"+num);
        }
        for (int num:insert(arr)) {
            Log.d("Test", "插入排序"+num);
        }
    }

    /**
     * 测试1：写出冒泡排序
     */
    public static int[] bubble(int[] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = arr.length-1; j >i ; j--) {
                if(arr[j]<arr[j-1]){
                    temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
        return arr;
    }

    /**
     * 测试2：写出选择排序
     */
    public static int[] select(int[] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]){
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                temp = arr[minIndex];
                arr[minIndex]= arr[i];
                arr[i] = temp;
            }
        }
        return arr;
    }

    /**
     * 测试3：写出插入排序
     */
    public static int[] insert(int[] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j > 0; j--) {
                if(arr[j]<arr[j-1]){
                    temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
            }
        }
        return arr;
    }

}

/*
冒泡排序
int temp;
for (int i = 0; i < arr.length-1; i++) {
        for (int j = arr.length-1; j >i ; j--) {
            if(arr[j]<arr[j-1]){
                temp = arr[j];
                arr[j]=arr[j-1];
                arr[j-1] = temp;
            }
        }
    }
 */
