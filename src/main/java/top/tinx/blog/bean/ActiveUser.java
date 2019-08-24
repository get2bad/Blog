package top.tinx.blog.bean;

/**
 * 创建人: Wills
 * 创建时间：2019/8/24 0:14
 * 描述:
 */
public class ActiveUser {
    private String userId;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ActiveUser{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
