package yxd.project1.context;

import java.util.List;

/**
 * Created by asus on 2018/1/17.
 */

public class sdds {

    /**
     * appCode : duowan
     * hasNext : true
     * retcode : 000000
     * dataType : post
     * data : [{"content":"[attach]40059100[/attach] ","publishDate":1516171046,"viewCount":58,"shareCount":null,"commentCount":5,"title":"LOL英雄联盟","id":"46126954","publishDateStr":"2018-01-17T06:37:26","posterScreenName":"暖栀暖暖","imageUrls":["http://att.bbs.duowan.com/forum/201801/17/14371611npzpusnp1evsjy.jpg"],"posterId":"64101858","likeCount":0,"url":"http://bbs.duowan.com/thread-46126954-1-1.html"}]
     * pageToken : 2
     */

    private String appCode;
    private boolean hasNext;
    private String retcode;
    private String dataType;
    private String pageToken;
    private List<DataBean> data;

    public String getAppCode() { return appCode;}

    public void setAppCode(String appCode) { this.appCode = appCode;}

    public boolean isHasNext() { return hasNext;}

    public void setHasNext(boolean hasNext) { this.hasNext = hasNext;}

    public String getRetcode() { return retcode;}

    public void setRetcode(String retcode) { this.retcode = retcode;}

    public String getDataType() { return dataType;}

    public void setDataType(String dataType) { this.dataType = dataType;}

    public String getPageToken() { return pageToken;}

    public void setPageToken(String pageToken) { this.pageToken = pageToken;}

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean {
        /**
         * content : [attach]40059100[/attach]
         * publishDate : 1516171046
         * viewCount : 58
         * shareCount : null
         * commentCount : 5
         * title : LOL英雄联盟
         * id : 46126954
         * publishDateStr : 2018-01-17T06:37:26
         * posterScreenName : 暖栀暖暖
         * imageUrls : ["http://att.bbs.duowan.com/forum/201801/17/14371611npzpusnp1evsjy.jpg"]
         * posterId : 64101858
         * likeCount : 0
         * url : http://bbs.duowan.com/thread-46126954-1-1.html
         */

        private String content;
        private int publishDate;
        private int viewCount;
        private Object shareCount;
        private int commentCount;
        private String title;
        private String id;
        private String publishDateStr;
        private String posterScreenName;
        private String posterId;
        private int likeCount;
        private String url;
        private List<String> imageUrls;

        public String getContent() { return content;}

        public void setContent(String content) { this.content = content;}

        public int getPublishDate() { return publishDate;}

        public void setPublishDate(int publishDate) { this.publishDate = publishDate;}

        public int getViewCount() { return viewCount;}

        public void setViewCount(int viewCount) { this.viewCount = viewCount;}

        public Object getShareCount() { return shareCount;}

        public void setShareCount(Object shareCount) { this.shareCount = shareCount;}

        public int getCommentCount() { return commentCount;}

        public void setCommentCount(int commentCount) { this.commentCount = commentCount;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getId() { return id;}

        public void setId(String id) { this.id = id;}

        public String getPublishDateStr() { return publishDateStr;}

        public void setPublishDateStr(String publishDateStr) { this.publishDateStr = publishDateStr;}

        public String getPosterScreenName() { return posterScreenName;}

        public void setPosterScreenName(String posterScreenName) { this.posterScreenName = posterScreenName;}

        public String getPosterId() { return posterId;}

        public void setPosterId(String posterId) { this.posterId = posterId;}

        public int getLikeCount() { return likeCount;}

        public void setLikeCount(int likeCount) { this.likeCount = likeCount;}

        public String getUrl() { return url;}

        public void setUrl(String url) { this.url = url;}

        public List<String> getImageUrls() { return imageUrls;}

        public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls;}
    }
}
