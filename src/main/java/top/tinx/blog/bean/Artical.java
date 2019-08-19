package top.tinx.blog.bean;

import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 *
 */
public class Artical {
    private int articalId;
    private int categoryId;
    private int status;
    private Date postTime;
    private int viewCount;
    private int userId;
    private String articalTitle;
    private String articalIntroduce;
    private String picIntroduceUpload;
    private String articalContent;
    private int isDenyComment;
    private int isLock;
    private int isSubmitTop;

    public Artical() {
    }

    public Artical(int articalId, int categoryId, int status, Date postTime, int viewCount, int userId, String articalTitle, String articalIntroduce, String picIntroduceUpload, String articalContent, int isDenyComment, int isLock, int isSubmitTop) {
        this.articalId = articalId;
        this.categoryId = categoryId;
        this.status = status;
        this.postTime = postTime;
        this.viewCount = viewCount;
        this.userId = userId;
        this.articalTitle = articalTitle;
        this.articalIntroduce = articalIntroduce;
        this.picIntroduceUpload = picIntroduceUpload;
        this.articalContent = articalContent;
        this.isDenyComment = isDenyComment;
        this.isLock = isLock;
        this.isSubmitTop = isSubmitTop;
    }

    public String getArticalContent() {
        return articalContent;
    }

    public void setArticalContent(String articalContent) {
        this.articalContent = articalContent;
    }

    public String getArticalTitle() {
        return articalTitle;
    }

    public void setArticalTitle(String articalTitle) {
        this.articalTitle = articalTitle;
    }

    public String getArticalIntroduce() {
        return articalIntroduce;
    }

    public void setArticalIntroduce(String articalIntroduce) {
        this.articalIntroduce = articalIntroduce;
    }

    public String getPicIntroduceUpload() {
        return picIntroduceUpload;
    }

    public void setPicIntroduceUpload(String picIntroduceUpload) {
        this.picIntroduceUpload = picIntroduceUpload;
    }

    public int getIsDenyComment() {
        return isDenyComment;
    }

    public void setIsDenyComment(int isDenyComment) {
        this.isDenyComment = isDenyComment;
    }

    public int getIsSubmitTop() {
        return isSubmitTop;
    }

    public void setIsSubmitTop(int isSubmitTop) {
        this.isSubmitTop = isSubmitTop;
    }

    public int getArticalId() {
        return articalId;
    }

    public void setArticalId(int articalId) {
        this.articalId = articalId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "articalId=" + articalId +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", postTime=" + postTime +
                ", viewCount=" + viewCount +
                ", userId=" + userId +
                ", articalTitle='" + articalTitle + '\'' +
                ", articalIntroduce='" + articalIntroduce + '\'' +
                ", picIntroduceUpload='" + picIntroduceUpload + '\'' +
                ", articalContent='" + articalContent + '\'' +
                ", isDenyComment=" + isDenyComment +
                ", isLock=" + isLock +
                ", isSubmitTop=" + isSubmitTop +
                '}';
    }
}
