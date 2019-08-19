package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.Permission;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:20
 * 描述:
 */
public interface PermissionMapper {

    @Select("select p.permission_id as permission_id,p.name as name,p.url as url\n" +
            "from tb_role_permission rp\n" +
            "LEFT JOIN tb_permission p on rp.permission_id = p.permission_id\n" +
            "where rp.role_id = 1;")
    List<Permission> findPermissionListByRoleId(@Param("role_id")int roleId);
}
