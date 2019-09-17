package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Permission;
import top.tinx.blog.bean.Role;
import top.tinx.blog.maaper.PermissionMapper;
import top.tinx.blog.maaper.RoleMapper;
import top.tinx.blog.service.RoleService;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:52
 * 描述:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> getRoleListByUserId(int userId) {
        List<Role> roleList = roleMapper.findRoleListByUserId(userId);
        return roleList;
    }
}
