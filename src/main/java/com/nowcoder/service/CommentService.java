package com.nowcoder.service;

import com.nowcoder.dao.CommentDao;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/1/30 下午9:48
 */


@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> getCommentsByEntity(int entityId, int entityType) {
        return commentDao.selectByEntity(entityId, entityType);
    }

    public int addComment(Comment comment){
        return commentDao.addComment(comment);
    }

    public int getCommentCounts(int entityId, int entityType){
        return commentDao.getCommentCount(entityId, entityType);
    }

    public int deleteComment(int entityId, int entityType){
        return commentDao.updateStatus(entityId,entityType,1);
    }
}
