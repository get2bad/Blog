package top.tinx.blog.bean;

import java.io.Serializable;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 22:07
 * 描述: 本实体类用于用户获取邮箱验证码临时储存的表
 */
public class TempUser implements Serializable {
    private int id;
    private String tempEmail;
    private String tempActivation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTempEmail() {
        return tempEmail;
    }

    public void setTempEmail(String tempEmail) {
        this.tempEmail = tempEmail;
    }

    public String getTempActivation() {
        return tempActivation;
    }

    public void setTempActivation(String tempActivation) {
        this.tempActivation = tempActivation;
    }

    @Override
    public String toString() {
        return "TempUser{" +
                "id=" + id +
                ", tempEmail='" + tempEmail + '\'' +
                ", tempActivation='" + tempActivation + '\'' +
                '}';
    }
}
