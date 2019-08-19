package top.tinx.blog.service;

import top.tinx.blog.bean.Role;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:51
 * 描述:
 */
public interface RoleService {

    public List<Role> getRoleListByUserId(int userId);
}
