package top.tinx.blog.bean;

import java.io.Serializable;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:04
 * 描述:
 */
public class RolePermission implements Serializable {

    private int id;
    private int roleId;
    private int permissionId;

    public RolePermission() {
    }

    public RolePermission(int id, int roleId, int permissionId) {
        this.id = id;
        this.roleId = roleId;
        this.permissionId = permissionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                '}';
    }
}
