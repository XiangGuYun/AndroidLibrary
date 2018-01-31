package yxd.project1.constant;

import yxd.project1.utils.StringUtils;

/**
 * Created by asus on 2018/1/1.
 */

public class URL {
    /*
     http://120.76.205.241:8000/news/qihoo
     ?kw=%E6%B8%B8%E6%88%8F
     &site=qq.com
     &pageToken=1
     &apikey=JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg
     */
    public static final String APIKEY="&JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";
    public static final String BASE_URL="http://120.76.205.241:8000/news/qihoo";
    public static final String KW="?kw=";
    public static final String SITE="&site=qq.com";
    public static final String PAGETOKEN="&pageToken=";
    public static final String URL_PART1="http://120.76.205.241:8000/news/qihoo?kw=";
    public static final String URL_PART2="&site=qq.com&pageToken=";
    public static final String URL_PART3="&apikey=JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";

    public static final String DEBUG_URL=
            "http://120.76.205.241:8000/news/qihoo?kw=%E6%B8%B8%E6%88%8F" +
                    "&site=qq.com&pageToken=1&apikey=JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";

    public static final String DUOWAN_BASE_URL="http://120.76.205.241:8000/video/duowan";
    public static final String DUOWAN_APIKEY="&apikey=JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";
    public static final String POST_APIKEY="JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";
//            "http://120.76.205.241:8000/video/duowan?kw=LOL&pageToken=2&apikey=JHQm5BKMIG3iqzJodxYvdbdcgz7o9nDbWcNNmXJWZbjRKzfKAuIzZAVZ7ZuRhsbg";
}
