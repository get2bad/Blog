package top.tinx.blog.service;

import top.tinx.blog.bean.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> getAllCommentByArticalId(int id);


    public List<Comment> getAllComment();

    public void insertComment(Comment comment);


}
