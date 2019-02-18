package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fresh.common.utils.Base64;
import com.fresh.manager.mapper.shop.CommentMapper;
import com.fresh.manager.mapper.shop.CommentPictureMapper;
import com.fresh.manager.pojo.shop.CommentEntity;
import com.fresh.manager.shop.service.CommentService;

/**
 * 用户评价Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-28 17:03:40
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentPictureMapper commentPictureMapper;

    @Override
    public CommentEntity queryObject(Integer id) {
        return commentMapper.queryObject(id);
    }

    @Override
    public List<CommentEntity> queryList(Map<String, Object> map) {
        List<CommentEntity> commentEntities = commentMapper.queryList(map);
        if (null != commentEntities && commentEntities.size() > 0) {
            for (CommentEntity commentEntity : commentEntities) {
                commentEntity.setContent(Base64.decode(commentEntity.getContent()));
            }
        }
        return commentEntities;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return commentMapper.queryTotal(map);
    }

    @Override
    public int save(CommentEntity comment) {
        return commentMapper.save(comment);
    }

    @Override
    public int update(CommentEntity comment) {
        return commentMapper.update(comment);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        //删除评论同时删除评论的图片
        commentPictureMapper.deleteByCommentId(id);
        return commentMapper.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return commentMapper.deleteBatch(ids);
    }

    @Override
    public int toggleStatus(CommentEntity comment) {
        CommentEntity commentEntity = queryObject(comment.getId());
        if (commentEntity.getStatus() == 1) {
            commentEntity.setStatus(0);
        } else if (commentEntity.getStatus() == 0) {
            commentEntity.setStatus(1);
        }
        return commentMapper.update(commentEntity);
    }
}
