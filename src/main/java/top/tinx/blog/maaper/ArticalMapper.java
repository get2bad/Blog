package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.*;
import top.tinx.blog.bean.Artical;

import java.util.List;

@Mapper
public interface ArticalMapper {

    @Insert("INSERT INTO blog.tb_artical " +
            "(artical_id, " +
            "category_id, " +
            "STATUS, " +
            "postTime, " +
            "viewCount, " +
            "userId, " +
            "articalTitle, " +
            "articalIntroduce, " +
            "picIntroduceUpload, " +
            "articalContent, " +
            "isDenyComment, " +
            "isLock, " +
            "isSubmitTop" +
            ")" +
            "VALUES" +
            "(#{artical.articalId}, " +
            "#{artical.categoryId}, " +
            "#{artical.status}, " +
            "#{artical.postTime}, " +
            "#{artical.viewCount}, " +
            "#{artical.userId}, " +
            "#{artical.articalTitle}, " +
            "#{artical.articalIntroduce}, " +
            "#{artical.picIntroduceUpload}, " +
            "#{artical.articalContent}, " +
            "#{artical.isDenyComment}, " +
            "#{artical.isLock}, " +
            "#{artical.isSubmitTop}" +
            ");")
    public void insertArtical(@Param("artical")Artical artical);

    @Select("select * from tb_artical where status = 0")
    public List<Artical> getAllJudgeArtical();

    @Select("select * from tb_artical where status = 1")
    public List<Artical> getAllPassArtical();

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
}
