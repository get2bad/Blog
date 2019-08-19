package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.User;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:15
 * 描述:
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where user_id=#{userId}")
    User findUserById(@Param("userId")int userId);

    @Select("select * from tb_user where userName=#{userName}")
    User findUserByUserName(@Param("userName")String userName);

    @Select("select * from tb_user where userName=#{userName} and password = #{password}")
    User findUserByUsernameAndPassword(@Param("userName")String userName,@Param("password")String password);
}
