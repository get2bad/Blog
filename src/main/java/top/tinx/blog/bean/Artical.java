package top.tinx.blog.bean;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Artical implements Serializable {
    private int articalId;
    private int categoryId;
    private String categoryName;
    private int status;
    private String postTime;
    private int viewCount;
    private int userId;
    private String userName;
    private String articalTitle;
    private String articalIntroduce;
    private String picIntroduceUpload;
    private String picIntroduceUploadUrl;
    private String articalContent;
    private int isDenyComment;
    private int isLock;
    private int isSubmitTop;

    public Artical() {
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPicIntroduceUploadUrl() {
        return picIntroduceUploadUrl;
    }

    public void setPicIntroduceUploadUrl(String picIntroduceUploadUrl) {
        this.picIntroduceUploadUrl = picIntroduceUploadUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "articalId=" + articalId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", status=" + status +
                ", postTime=" + postTime +
                ", viewCount=" + viewCount +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", articalTitle='" + articalTitle + '\'' +
                ", articalIntroduce='" + articalIntroduce + '\'' +
                ", picIntroduceUpload='" + picIntroduceUpload + '\'' +
                ", picIntroduceUploadUrl='" + picIntroduceUploadUrl + '\'' +
                ", articalContent='" + articalContent + '\'' +
                ", isDenyComment=" + isDenyComment +
                ", isLock=" + isLock +
                ", isSubmitTop=" + isSubmitTop +
                '}';
    }
}
