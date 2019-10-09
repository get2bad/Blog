package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.*;
import top.tinx.blog.bean.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from tb_comment where artical_id = #{id} AND comment_status = 1")
    public List<Comment> getAllCommentById(@Param("id") int id);

    @Select("select * from tb_comment")
    public List<Comment> getAllCommment();

    @Select("select count(*) from tb_comment")
    public int getAllCommentCount();

    @Insert("insert into tb_comment(artical_id,user_id,comment_content,comment_status,comment_postTime) values(#{comment.articalId},#{comment.userId},#{comment.commentContent},#{comment.commentStatus},#{comment.commentPostTime})")
    public void insertComment(@Param("comment") Comment comment);

    @Update("update tb_comment set comment_status = #{status} where comment_id = #{id}")
    public void setCommentStatus(@Param("id")String id,@Param("status")String status);

    @Delete("delete from tb_comment where comment_id = #{id}")
    public void deleteComment(@Param("id")String id);

    @Select("select count(*) from tb_comment where artical_id = #{id};")
    public int getCommentCountByArticalId(@Param("id")String id);

    @Update("update tb_artical set viewCount = viewCount + 1 where artical_id = #{id};")
    public void setViewCountNum(@Param("id")String id);
}