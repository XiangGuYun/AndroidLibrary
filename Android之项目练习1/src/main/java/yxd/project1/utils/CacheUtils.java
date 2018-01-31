package yxd.project1.utils;

import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yxd.project1.base.common.L;
import yxd.project1.bean.News;
import yxd.project1.context.MyApplication;
import yxd.project1.utils.disk.DiskLruCacheHelper;

/**
 * Created by asus on 2018/1/1.
 */

public class CacheUtils {

    private static CacheUtils loader;

    private CacheUtils(){}

    public static CacheUtils.Instance getInstance(){
        return Instance.instance;
    }

    public static class Instance{
        private static Instance instance = new Instance();
        private LruCache<String, News> lruCache;
        private DiskLruCacheHelper helper;
        private List<String> keys = new ArrayList<>();
        private Gson gson;
        /*
        初始化缓存对象
         */
        private Instance(){
            //设置缓存大小
            int maxMemSize = (int) (Runtime.getRuntime().maxMemory()/8);
            lruCache = new LruCache<>(maxMemSize);
            try {
                helper = new DiskLruCacheHelper(MyApplication.getContext());
            } catch (IOException e) {
                e.printStackTrace();
            }
            gson = new Gson();
        }

        public DiskLruCacheHelper getDiskHelper(){
            return helper;
        }

        public void saveDataToCache(String fragmentTag, News news){
            L.d("保存到了缓存中");
            lruCache.put(fragmentTag, news);
            helper.put(fragmentTag, gson.toJson(news));
        }

        public void saveListToCache(String listTag, List<Integer> list){
            L.d("将pos数组保存到了缓存中");
            helper.put(listTag, gson.toJson(list));
        }

        public List<Integer> getListFromCache(String listTag){
            if(!TextUtils.isEmpty(listTag)){
                if(helper.get(listTag)!=null){
                    return gson.fromJson(helper.getAsString(listTag), new TypeToken<List<Integer>>(){}.getType());
                }
            }
            return null;
        }

        public Object getDataFromDiskCache(String fragmentTag, Class aClass) {
            if(!TextUtils.isEmpty(fragmentTag)){
                if(helper.get(fragmentTag)!=null){
                    L.d("从本地缓存中取出了数据");
                    return gson.fromJson(helper.getAsString(fragmentTag), aClass);
                }
            }
            return null;
        }

        public <T> void saveDataToDiskCache(String fragmentTag, T t) {
            if(!TextUtils.isEmpty(fragmentTag)){
                helper.put(fragmentTag, gson.toJson(t));
            }
        }

        public News getDataFromCache(String fragmentTag) {
            if(!TextUtils.isEmpty(fragmentTag)){
                if(lruCache.get(fragmentTag) != null){
                    L.d("从内存缓存中取出了数据");
                    return lruCache.get(fragmentTag);
                }else if(helper.get(fragmentTag)!=null){
                    L.d("从本地缓存中取出了数据");
                    return gson.fromJson(helper.getAsString(fragmentTag), News.class);
                }
            }
            return null;
        }

        /*
        清空缓存
         */
        public void clear(){
            for (String key:keys
                    ) {
                lruCache.remove(key);
                keys.remove(key);
            }

        }


    }

}
