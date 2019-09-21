package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.Category;
@Mapper
public interface CategoryMapper {

    @Select("select * from tb_category where category_id = #{id}")
    Category getCategoryById(@Param("id")int id);
}
