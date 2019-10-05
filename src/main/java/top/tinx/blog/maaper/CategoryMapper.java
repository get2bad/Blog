package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.*;
import top.tinx.blog.bean.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from tb_category where category_id = #{id}")
    Category getCategoryById(@Param("id")int id);

    @Select("select * from tb_category")
    List<Category> getAllCategorys();

    @Delete("delete from tb_category where category_id=#{id}")
    void deleteCategoryById(@Param("id") String id);

    @Insert("insert into tb_category(category_name,category_url) values(#{c.categoryName},#{c.categoryUrl})")
    void insertCategory(@Param("c") Category category);
}
