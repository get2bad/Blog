package top.tinx.blog.bean;

/**
 * 创建人: Wills
 * 创建时间：2019/8/21 15:09
 * 描述:
 */
public class Login{
    private String userName;
    private String password;
    private String vaptchaCode;
    private String remember;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVaptchaCode() {
        return vaptchaCode;
    }

    public void setVaptchaCode(String vaptchaCode) {
        this.vaptchaCode = vaptchaCode;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return "TestLogin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", vaptchaCode='" + vaptchaCode + '\'' +
                ", remember='" + remember + '\'' +
                '}';
    }
}
