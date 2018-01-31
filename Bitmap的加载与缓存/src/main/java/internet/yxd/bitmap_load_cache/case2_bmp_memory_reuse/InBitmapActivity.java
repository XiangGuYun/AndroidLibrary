package internet.yxd.bitmap_load_cache.case2_bmp_memory_reuse;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import internet.yxd.bitmap_load_cache.R;

public class InBitmapActivity extends AppCompatActivity {

    private ImageView imageView;
    private String filePathOne = Environment.getExternalStorageDirectory()+"/test2.jpg";
    private String filePathTwo = Environment.getExternalStorageDirectory()+"/test1.jpg";
    private Bitmap bitmapOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_bitmap);
        imageView = findViewById(R.id.iv);
        checkPerm();//检查SD卡权限
    }

    /*
    显示Bitmap1
     */
    public void image1(View view) {
        //解析Bitmap1并显示到ImageView上
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inPreferredConfig= Bitmap.Config.RGB_565;
        options1.inSampleSize=1;
        bitmapOne = BitmapFactory.decodeFile(filePathOne, options1);
        imageView.setImageBitmap(bitmapOne);
    }

    /*
    显示Bitmap2
     */
    public void image2(View view) {
        //位图工厂选项
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        //只解析边框，先不将Bitmap加入到内存中
        options2.inJustDecodeBounds = true;
        options2.inPreferredConfig= Bitmap.Config.RGB_565;
        options2.inSampleSize=2;//通过修改inSampleSize可以使bitmap1小于或等于bitmap2以实现内存重用
        //解析Bitmap2的边框
        BitmapFactory.decodeFile(filePathTwo, options2);
        //解析完后判断是否能重用Bitmap1的内存
        if (canUseForInBitmap(bitmapOne, options2)) {
            options2.inMutable = true;//可修改
            //将Bitmap1设置到Bitmap2工厂选项的inBitmap属性中
            //Bitmap2将会重用Bitmap1的内存
            options2.inBitmap = bitmapOne;
            Log.d("Test", "bitmap2重用了bitmap1的内存");
        }
        //解析并显示图片
        options2.inJustDecodeBounds = false;
        Bitmap bitmapTwo = BitmapFactory.decodeFile(filePathTwo, options2);
        imageView.setImageBitmap(bitmapTwo);
    }

    /*
    检查是否能使用InBitmap属性
    candidate：原Bitmap
    targetOptions：目标Bitmap工厂选项
     */
    public static boolean canUseForInBitmap(Bitmap candidate, BitmapFactory.Options targetOptions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4以后的版本新图片的尺寸大于或等于就图片且解码格式相同即可
            int width = targetOptions.outWidth / targetOptions.inSampleSize;
            int height = targetOptions.outHeight / targetOptions.inSampleSize;
            int byteCount = width * height * getBytesPerPixel(candidate.getConfig());
            try {
                return byteCount <= candidate.getAllocationByteCount();
            } catch (NullPointerException e) {
                return byteCount <= candidate.getHeight() * candidate.getRowBytes();
            }
        }
        // 在4.4以前的版本, 前后Bitmap尺寸必须相一致，前inSampleSize要为1
        return candidate.getWidth() == targetOptions.outWidth
                && candidate.getHeight() == targetOptions.outHeight
                && targetOptions.inSampleSize == 1;
    }

    /*
    获取每像素的字节数
     */
    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }

        int bytesPerPixel;
        switch (config) {
            case ALPHA_8:
                bytesPerPixel = 1;
                break;
            case RGB_565:
            case ARGB_4444:
                bytesPerPixel = 2;
                break;
            case ARGB_8888:
            default:
                bytesPerPixel = 4;
                break;
        }
        return bytesPerPixel;
    }

    /*
    检查SD卡权限
     */
    private void checkPerm() {
        if(ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
