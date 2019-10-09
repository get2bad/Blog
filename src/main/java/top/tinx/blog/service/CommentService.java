package top.tinx.blog.service;

import top.tinx.blog.bean.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> getAllCommentByArticalId(int id);


    public List<Comment> getAllComment();

    public void insertComment(Comment comment);

    public void setCommentStatus(String id,String status);

    public void deleteCommentById(String id);

    public int getCommentCountByArticalId(String id);

    public void setViewCountNum(String id);

    public int getAllCommentCount();
}
