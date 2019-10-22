package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.*;
import top.tinx.blog.bean.Artical;
import top.tinx.blog.bean.ArticalPieInfo;

import java.util.List;

@Mapper
public interface ArticalMapper {

    @Insert("INSERT INTO blog.tb_artical " +
            "( " +
            "category_id, " +
            "STATUS, " +
            "postTime, " +
            "viewCount, " +
            "userId, " +
            "articalTitle, " +
            "articalIntroduce, " +
            "picIntroduceUpload, " +
            "picIntroduceUploadUrl, " +
            "articalContent, " +
            "isDenyComment, " +
            "isLock, " +
            "isSubmitTop" +
            ")" +
            "VALUES" +
            "(" +
            "#{artical.categoryId}, " +
            "#{artical.status}, " +
            "#{artical.postTime}, " +
            "#{artical.viewCount}, " +
            "#{artical.userId}, " +
            "#{artical.articalTitle}, " +
            "#{artical.articalIntroduce}, " +
            "#{artical.picIntroduceUpload}, " +
            "#{artical.picIntroduceUploadUrl}, " +
            "#{artical.articalContent}, " +
            "#{artical.isDenyComment}, " +
            "#{artical.isLock}, " +
            "#{artical.isSubmitTop}" +
            ");")
    public void insertArtical(@Param("artical")Artical artical);

    @Select("select * from tb_artical where status = 0")
    public List<Artical> getAllJudgeArtical();

    @Select("select * from tb_artical where status = 1 order by artical_id desc limit #{start},#{end}")
    public List<Artical> getAllPassArtical(@Param("start")int start,@Param("end")int end);

    @Update("UPDATE blog.tb_artical \n" +
            "\tSET\n" +
            "\tSTATUS = '0'  \n" +
            "\tWHERE\n" +
            "\tartical_id = #{id} ;")
    public void backJudgeArtical(@Param("id") String id);

    @Update("UPDATE blog.tb_artical \n" +
            "\tSET\n" +
            "\tSTATUS = '1'  \n" +
            "\tWHERE\n" +
            "\tartical_id = #{id} ;")
    public void passArtical(@Param("id") String id);

    @Select("select * from tb_artical where artical_id = #{id}")
    public Artical findArticalById(@Param("id") int id);

    @Select("select COUNT(*) from tb_artical")
    public int getAllArticalCount();

    @Select("select * from tb_artical where category_id=#{id} and status = #{s} and articalTitle like #{a}")
    public List<Artical> getArticalByConditions(@Param("id")String id,@Param("s")String s,@Param("a")String a);

    @Select("select sum(viewCount) from tb_artical")
    public Integer getAllViewCount();

    @Select("select count(category_id)as count,category_id from tb_artical GROUP BY category_id;")
    public List<ArticalPieInfo> getAllInfos();

    @Delete("delete from tb_artical where artical_id = #{id}")
    public void deleteArticalById(@Param("id") String id);
}
