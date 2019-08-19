package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.tinx.blog.bean.Permission;
import top.tinx.blog.maaper.PermissionMapper;
import top.tinx.blog.service.PermissionService;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:55
 * 描述:
 */
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findPermissionListByRoleId(int roleId) {
        return permissionMapper.findPermissionListByRoleId(roleId);
    }
}
