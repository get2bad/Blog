package top.tinx.blog.bean;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:05
 * 描述:
 */
public class Role {

    private int roleId;
    private String authName;
    private String authDescription;
    private List<Permission> permissionList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Role() {
    }

    public Role(int roleId, String authName, String authDescription, List<Permission> permissionList) {
        this.roleId = roleId;
        this.authName = authName;
        this.authDescription = authDescription;
        this.permissionList = permissionList;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDescription() {
        return authDescription;
    }

    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", authName='" + authName + '\'' +
                ", authDescription='" + authDescription + '\'' +
                ", permissionList=" + permissionList +
                '}';
    }
}
