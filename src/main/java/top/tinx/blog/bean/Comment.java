package top.tinx.blog.bean;

import java.io.Serializable;

public class Comment implements Serializable {

    private int commentId;
    private int userId;
    private int articalId;
    private String articalName;
    private String userName;
    private String commentContent;
    private String commentPostTime;
    private  int commentStatus;

    public Comment(int userId,int articalId, String commentContent, int commentStatus) {
        this.userId = userId;
        this.articalId = articalId;
        this.commentContent = commentContent;
        this.commentStatus = commentStatus;
    }

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getArticalId() {
        return articalId;
    }

    public void setArticalId(int articalId) {
        this.articalId = articalId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus) {
        this.commentStatus = commentStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentPostTime() {
        return commentPostTime;
    }

    public void setCommentPostTime(String commentPostTime) {
        this.commentPostTime = commentPostTime;
    }

    public String getArticalName() {
        return articalName;
    }

    public void setArticalName(String articalName) {
        this.articalName = articalName;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", articalId=" + articalId +
                ", userName='" + userName + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentPostTime='" + commentPostTime + '\'' +
                ", commentStatus=" + commentStatus +
                '}';
    }
}
