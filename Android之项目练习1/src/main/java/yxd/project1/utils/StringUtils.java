package yxd.project1.utils;

import yxd.project1.constant.URL;

/**
 * Created by asus on 2018/1/1.
 */

public class StringUtils {


    public static String appendStr(String...strings){
       StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
        }
        return builder.toString();
    }



    public static String appendURL(String keyWord, String pageToken){
        return StringUtils.appendStr(URL.URL_PART1,keyWord,URL.URL_PART2,pageToken,URL.URL_PART3);
    }

}
