package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 创建人: Wills
 * 创建时间：2019/8/22 22:13
 * 描述:
 */
@Mapper
public interface TempUserMapper {

    @Insert("insert into tb_temp_user(temp_email,temp_activation) values(#{email},#{activationCode})")
    public void insertTempUser(@Param("email")String email,@Param("activationCode")String activationCode);

    @Select("select temp_activation as activationCode from tb_temp_user where temp_email = #{email};")
    public String getRegActivationCode(@Param("email")String email);
}
