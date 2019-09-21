package top.tinx.blog.maaper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.tinx.blog.bean.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from tb_comment where id = #{id}")
    public List<Comment> getAllCommentById(@Param("id") int id);

    @Select("select * from tb_comment")
    public List<Comment> getAllCommment();

    @Insert("insert into tb_comment(artical_id,user_id,comment_content,comment_status,comment_postTime) values(#{comment.articalId},#{comment.userId},#{comment.commentContent},#{comment.commentStatus},#{comment.commentPostTime})")
    public void insertComment(@Param("comment") Comment comment);
}