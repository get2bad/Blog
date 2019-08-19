package top.tinx.blog.bean;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:11
 * 描述:
 */
public class Permission {
    private int permissionId;
    private String name;
    private String url;

    public Permission() {
    }

    public Permission(int permissionId, String name, String url) {
        this.permissionId = permissionId;
        this.name = name;
        this.url = url;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
