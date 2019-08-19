package top.tinx.blog.bean;

import java.util.Date;
import java.util.List;

public class User {

    private int userId;
    private String userName;
    private String password;
    private Role role;
    private int sex;
    private String phone;
    private String QQ;
    private String Email;
    private String address;
    private int score;
    private String lastSignIP;
    private Date birthday;
    private String description;
    private String userIconLocation;
    private int isDenySignIn;
    private String activationCode;
    private int isActived;
    private List<Role> roleList;

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public User() {
    }

    public User(int userId, String userName, String password, Role role, int sex, String phone, String QQ, String email, String address, int score, String lastSignIP, Date birthday, String description, String userIconLocation, int isDenySignIn, String activationCode, int isActived, List<Role> roleList) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.sex = sex;
        this.phone = phone;
        this.QQ = QQ;
        Email = email;
        this.address = address;
        this.score = score;
        this.lastSignIP = lastSignIP;
        this.birthday = birthday;
        this.description = description;
        this.userIconLocation = userIconLocation;
        this.isDenySignIn = isDenySignIn;
        this.activationCode = activationCode;
        this.isActived = isActived;
        this.roleList = roleList;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLastSignIP() {
        return lastSignIP;
    }

    public void setLastSignIP(String lastSignIP) {
        this.lastSignIP = lastSignIP;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserIconLocation() {
        return userIconLocation;
    }

    public void setUserIconLocation(String userIconLocation) {
        this.userIconLocation = userIconLocation;
    }

    public int getIsDenySignIn() {
        return isDenySignIn;
    }

    public void setIsDenySignIn(int isDenySignIn) {
        this.isDenySignIn = isDenySignIn;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public int getIsActived() {
        return isActived;
    }

    public void setIsActived(int isActived) {
        this.isActived = isActived;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", QQ='" + QQ + '\'' +
                ", Email='" + Email + '\'' +
                ", address='" + address + '\'' +
                ", score=" + score +
                ", lastSignIP='" + lastSignIP + '\'' +
                ", birthday=" + birthday +
                ", description='" + description + '\'' +
                ", userIconLocation='" + userIconLocation + '\'' +
                ", isDenySignIn=" + isDenySignIn +
                ", activationCode='" + activationCode + '\'' +
                ", isActived=" + isActived +
                ", roleList=" + roleList +
                '}';
    }
}
