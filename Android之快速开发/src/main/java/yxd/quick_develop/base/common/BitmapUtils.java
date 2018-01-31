package yxd.quick_develop.base.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by asus on 2017/12/12.
 */

public class BitmapUtils {

    /**
     * 压缩文件路径下的图片
     * @param filePath 文件路径
     * @param targetW 想要的宽度值
     * @param targetH 想要的高度值
     * @return
     */
    public static Bitmap decodeBitmap(String filePath, int targetW, int targetH){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置只解码图片的边框（宽高）数据，只为测出采样率
        options.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片像素格式的首选配置
        BitmapFactory.decodeFile(filePath, options);//预加载
        //获取图片的原始宽高
        int originalW = options.outWidth;
        int originalH = options.outHeight;
        //设置采样大小
        options.inSampleSize = getSimpleSize(originalW, originalH, targetW, targetH);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap compressQuality(Bitmap bitmap, int quality){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap compressSize(Bitmap bitmap, int targetW, int targetH){
        return Bitmap.createScaledBitmap(bitmap, targetW, targetH, true);
    }

    /*
    计算采样率
     */
    private static int getSimpleSize(int originalW, int originalH, int targetW, int targetH) {
        int sampleSize = 1;
        if(originalW > originalH && originalW > targetW){//以宽度来计算采样值
            sampleSize = originalW/targetW;
        }else if(originalW < originalH && originalH > targetH){
            sampleSize = originalH/targetH;
        }
        if(sampleSize <= 0){
            sampleSize = 1;
        }
        return sampleSize;
    }

}
