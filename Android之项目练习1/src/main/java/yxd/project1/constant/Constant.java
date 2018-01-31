package yxd.project1.constant;

/**
 * Created by asus on 2018/1/2.
 */

public class Constant {

    public static final int QUIT_ACTIVITY = 0;
    public static final int CLOSE_DRAWER_EDIT = 1;
    public static final int CLOSE_DRAWER_SEARCH = 2;
    public static final int QUIT_WEB = 3;
    public static final int QUIT_SEARCH_FRAGMENT = 4;
    public static final String VIDEO_URL = "video_url";

    public static boolean IS_LOGIN = false;
    public static final String CURRENT_USERNAME="current_username";
    /*
    默认：社会、历史、动漫、娱乐、体育、游戏、美女、校园、军事、搞笑
     */
    public static String KEYWORD;//关键字
    public static String KEYWORD_UNSELECT = "keyword_unselect";//未被选择的关键字

    public static String[] defaultKWs = {"社会","历史","动漫","娱乐","体育","游戏","美女","校园","军事","搞笑"};
    public static String[] defaultUnselectKWs = {"军事","科技","财经","生活","国际","BTV","时间号","房产","NBA","电影"
            ,"电视","数码","健康","亲子","美食","足球","时尚","星座","旅游","证券","汽车","情感","文化"};
    public static final String FIRST_LAUNCHER = "first_launcher";

    public static final String LOADED_TAG = "hasLoaded";

    public static final int NO_IMG = 0;//无图列表项
    public static final int ONE_IMG = 1;//1图列表项flag
    public static final int THREE_IMG = 2;//3图列表项flag
    public static final int ON_GET_DATA = 0x111;//handler的flag
}
