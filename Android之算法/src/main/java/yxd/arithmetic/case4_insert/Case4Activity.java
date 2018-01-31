package yxd.arithmetic.case4_insert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import yxd.arithmetic.R;

public class Case4Activity extends AppCompatActivity {

    int[] arr={10,7,4,8,2,1,3,6,9,5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case4);
        int[] newArr = sortInsert(arr);
        for (int i = 0; i < newArr.length; i++) {
            Log.d("Test", "插入排序："+newArr[i]);
        }
    }

    public int[] sortInsert(int[] arr){
        int temp;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j>0 ; j--) {
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        
        return arr;
    }
}
