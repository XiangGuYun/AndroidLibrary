package yxd.arithmetic.case3_select;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import yxd.arithmetic.R;

public class Case3Activity extends AppCompatActivity {

    int[] arr={10,7,4,8,2,1,3,6,9,5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case3);
        int[] newArr = sortSelect(arr);
        for (int i = 0; i < newArr.length; i++) {
            Log.d("Test", newArr[i]+"");
        }
    }

    public int[] sortSelect(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
           int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<arr[minIndex]){
                   minIndex = j;
                }
            }
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }

}
