package top.tinx.blog.service;

import top.tinx.blog.bean.Permission;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:54
 * 描述:
 */
public interface PermissionService {

    public List<Permission> findPermissionListByRoleId(int roleId);
}
