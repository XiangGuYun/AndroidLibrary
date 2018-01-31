package yxd.project1.bean;

import java.util.List;

/**
 * Created by asus on 2018/1/12.
 */

public class Videos {
    private List<VideoData> data;
    private String dataType;
    private String retcode;
    private String appCode;
    private boolean hasNext;
    private String pageToken;

    public List<VideoData> getData() {
        return data;
    }

    public void setData(List<VideoData> data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public String getPageToken() {
        return pageToken;
    }

    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    public class VideoData{
        private List<FileOption> fileOptions;
        private int viewCount;
        private String description;
        private String catPathKey;
        private String partList;
        private String posterScreenName;
        private String publishDateStr;
        private String id;
        private String favoriteCount;
        private long publishDate;
        private String tags;
        private boolean isFree;
        private boolean memberOnly;
        private String commentCount;
        private String dislikeCount;
        private String posterId;
        private String[] videoUrls;
        private int danmakuCount;
        private double durationMin;
        private String title;
        private String coverUrl;
        private String mediaType;
        private String likeCount;
        private String url;



        public List<FileOption> getFileOptions() {
            return fileOptions;
        }

        public void setFileOptions(List<FileOption> fileOptions) {
            this.fileOptions = fileOptions;
        }

        public int getViewCount() {
            return viewCount;
        }

        public void setViewCount(int viewCount) {
            this.viewCount = viewCount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCatPathKey() {
            return catPathKey;
        }

        public void setCatPathKey(String catPathKey) {
            this.catPathKey = catPathKey;
        }

        public String getPartList() {
            return partList;
        }

        public void setPartList(String partList) {
            this.partList = partList;
        }

        public String getPosterScreenName() {
            return posterScreenName;
        }

        public void setPosterScreenName(String posterScreenName) {
            this.posterScreenName = posterScreenName;
        }

        public String getPublishDateStr() {
            return publishDateStr;
        }

        public void setPublishDateStr(String publishDateStr) {
            this.publishDateStr = publishDateStr;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(String favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public long getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(long publishDate) {
            this.publishDate = publishDate;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public boolean isFree() {
            return isFree;
        }

        public void setFree(boolean free) {
            isFree = free;
        }

        public boolean isMemberOnly() {
            return memberOnly;
        }

        public void setMemberOnly(boolean memberOnly) {
            this.memberOnly = memberOnly;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getDislikeCount() {
            return dislikeCount;
        }

        public void setDislikeCount(String dislikeCount) {
            this.dislikeCount = dislikeCount;
        }

        public String getPosterId() {
            return posterId;
        }

        public void setPosterId(String posterId) {
            this.posterId = posterId;
        }

        public String[] getVideoUrls() {
            return videoUrls;
        }

        public void setVideoUrls(String[] videoUrls) {
            this.videoUrls = videoUrls;
        }

        public int getDanmakuCount() {
            return danmakuCount;
        }

        public void setDanmakuCount(int danmakuCount) {
            this.danmakuCount = danmakuCount;
        }

        public double getDurationMin() {
            return durationMin;
        }

        public void setDurationMin(double durationMin) {
            this.durationMin = durationMin;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public class FileOption{
            private String id;
            private String format;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }

}
