package top.tinx.blog.bean;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 13:30
 * 描述:
 */
public class UserEmailCode {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEmailCode{" +
                "email='" + email + '\'' +
                '}';
    }
}
