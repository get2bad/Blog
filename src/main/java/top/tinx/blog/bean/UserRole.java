package top.tinx.blog.bean;

import java.io.Serializable;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:04
 * 描述:
 */
public class UserRole implements Serializable {
    private int id;
    private int userId;
    private int roleId;
    private String remarks;

    public UserRole() {
    }

    public UserRole(int id, int userId, int roleId, String remarks) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
