package internet.yxd.bitmap_load_cache.case1_compress_bitmp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import internet.yxd.bitmap_load_cache.R;

/*
Bitmap的重要属性
1.inJustDecodeBounds
2.outWidth&outHeight
3.inSampleSize

常用缓存策略
1.LruCache
2.DiskLruCache
3.SQLite（不常用）

Lru：最近最少使用算法，内部采用LinkedHashMap，取代了SoftReference

DiskLruCache
选择缓存路径
/sdcard/Android/data/包名/cache
/data/data/Android/data/包名/cahce
 */
public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    String imgUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/" +
            "superman/img/logo/logo_white_fe6da1ec.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv);

    }

    public void download(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }else{
                SimpleImageLoader.Instance instance = SimpleImageLoader.getInstance();
                instance.displayImg(imageView, imgUrl);
            }
        }else{
            SimpleImageLoader.Instance instance = SimpleImageLoader.getInstance();
            instance.displayImg(imageView, imgUrl);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    SimpleImageLoader.Instance instance = SimpleImageLoader.getInstance();
                    instance.displayImg(imageView, imgUrl);
                }else{
                    Toast.makeText(this, "权限未授予", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void clear(View view) {
        SimpleImageLoader.getInstance().clear();
    }
}
