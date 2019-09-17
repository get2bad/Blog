package top.tinx.blog.maaper;

import com.fasterxml.jackson.databind.JavaType;
import org.apache.ibatis.annotations.*;
import top.tinx.blog.bean.User;

import java.util.List;

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

    /**
     * 根据用户ID修改用户最后登陆的IP地址
     * @param ip
     * @param userId
     */
    @Update("update tb_user set lastSignInIP = #{IP} where user_id = #{user_id};")
    void updateLoginIP(@Param("IP")String ip,@Param("user_id")int userId);

    /**
     * 根据用户ID修改用户最后登陆的IP地址
     * @param ip
     * @param userName
     */
    @Update("update tb_user set lastSignInIP = #{IP} where userName = #{userName};")
    void updateLoginIPByUserName(@Param("IP")String ip,@Param("userName")String userName);

    @Select("SELECT COUNT(Email) as Email from tb_user where Email = #{email};")
    @Results(
            value = {
                    @Result(property = "Email",column = "Email")
            }
    )
    int getRepeatEmial(@Param("email")String email);

    @Insert("insert into tb_user(userName,password,registDate,role_id,sex,phone,Email,score," +
            "lastSignInIP,birthday,isDenySignIn,activationCode,isActived) " +
            "VALUES(#{user.userName},#{user.password},#{user.registDate},5,#{user.sex},#{user.phone},#{user.Email},0,#{user.lastSignIP}," +
            "#{user.birthday},0,#{user.activationCode},1);")
    void insertNewUser(@Param("user") User user);

    @Select("select count(userName) as userName from tb_user where userName = #{userName};")
    @Results(
            value = {
                    @Result(property = "userName",column = "userName")
            }
    )
    int getUserNameCount(@Param("userName")String userName);

    @Select("select count(Email) as Email from tb_user where Email = #{Email};")
    @Results(
            value = {
                    @Result(property = "Email",column = "Email")
            }
    )
    int getEmailCount(@Param("Email")String Email);

    @Select("select * from tb_user")
    List<User> getAllUserInfo();


}
