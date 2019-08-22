package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Update("update tb_user set lastSignInIP = #{IP} where user_id = #{user_id};")
    void updateLoginIP(@Param("IP")String ip,@Param("user_id")String userId);
}
