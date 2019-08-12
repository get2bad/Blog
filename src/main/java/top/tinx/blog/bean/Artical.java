package top.tinx.blog.bean;

import org.springframework.context.annotation.Bean;

/**
 *
 */
public class Artical {
    private String articalTitle;
    private String articalIntroduce;
    private String picIntroduceUpload;
    private String articalType;
    private String articalAuth;
    private int isDenyComment;
    private int isSubmitTop;

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

    public String getArticalType() {
        return articalType;
    }

    public void setArticalType(String articalType) {
        this.articalType = articalType;
    }

    public String getArticalAuth() {
        return articalAuth;
    }

    public void setArticalAuth(String articalAuth) {
        this.articalAuth = articalAuth;
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

    @Override
    public String toString() {
        return "Artical{" +
                "articalTitle='" + articalTitle + '\'' +
                ", articalIntroduce='" + articalIntroduce + '\'' +
                ", picIntroduceUpload='" + picIntroduceUpload + '\'' +
                ", articalType='" + articalType + '\'' +
                ", articalAuth='" + articalAuth + '\'' +
                ", isDenyComment=" + isDenyComment +
                ", isSubmitTop=" + isSubmitTop +
                '}';
    }
}
