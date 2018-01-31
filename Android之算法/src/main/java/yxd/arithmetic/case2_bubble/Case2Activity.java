package yxd.arithmetic.case2_bubble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import yxd.arithmetic.R;

public class Case2Activity extends AppCompatActivity {

    int[] arr={10,7,4,8,2,1,3,6,9,5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);

        int[] newArr = sortBubble(arr);
        for (int i = 0; i < newArr.length; i++) {
            Log.d("Test", newArr[i]+"");
        }

    }

    public int[] sortBubble(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = arr.length-1; j >i ; j--) {
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
    }
}
