package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.tinx.blog.bean.Role;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:20
 * 描述:
 */
@Mapper
public interface RoleMapper {

    @Select("select r.role_id as role_id,r.authName as authName,r.authDescription as authDescription\n" +
            "from tb_user_role ur\n" +
            "LEFT JOIN tb_role r on r.role_id = ur.role_id\n" +
            "where ur.user_id = #{userId};")
    @Results(
            value = {
                    @Result(id=true,property = "roleId",column = "role_id"),
                    @Result(property = "authName",column = "authName"),
                    @Result(property = "authDescription",column = "authDescription"),
                    @Result(property = "permissionList",column = "permission_id",
                    many = @Many(select = "top.tinx.blog.maaper.PermissionMapper.findPermissionListByRoleId",fetchType = FetchType.DEFAULT))
            }
    )
    List<Role> findRoleListByUserId(@Param("userId")int userId);
}
