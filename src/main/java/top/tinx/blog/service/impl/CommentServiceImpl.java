package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Comment;
import top.tinx.blog.maaper.CommentMapper;
import top.tinx.blog.service.CommentService;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getAllCommentByArticalId(int id) {
        return commentMapper.getAllCommentById(id);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.getAllCommment();
    }

    @Override
    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void setCommentStatus(String id, String status) {
        commentMapper.setCommentStatus(id,status);
    }

    @Override
    public void deleteCommentById(String id) {
        commentMapper.deleteComment(id);
    }

    @Override
    public int getCommentCountByArticalId(String id) {
        return commentMapper.getCommentCountByArticalId(id);
    }

    @Override
    public void setViewCountNum(String id) {
        commentMapper.setViewCountNum(id);
    }

    @Override
    public int getAllCommentCount() {
        return commentMapper.getAllCommentCount();
    }
}
