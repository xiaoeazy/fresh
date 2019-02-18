package com.fresh.manager.shop.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresh.manager.mapper.shop.CommentPictureMapper;
import com.fresh.manager.pojo.shop.CommentPictureEntity;
import com.fresh.manager.shop.service.CommentPictureService;

/**
 * 评价图片Service实现类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-29 14:45:55
 */
@Service("commentPictureService")
public class CommentPictureServiceImpl implements CommentPictureService {
    @Autowired
    private CommentPictureMapper commentPictureMapper;

    @Override
    public CommentPictureEntity queryObject(Integer id) {
        return commentPictureMapper.queryObject(id);
    }

    @Override
    public List<CommentPictureEntity> queryList(Map<String, Object> map) {
        return commentPictureMapper.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return commentPictureMapper.queryTotal(map);
    }

    @Override
    public int save(CommentPictureEntity commentPicture) {
        return commentPictureMapper.save(commentPicture);
    }

    @Override
    public int update(CommentPictureEntity commentPicture) {
        return commentPictureMapper.update(commentPicture);
    }

    @Override
    public int delete(Integer id) {
        return commentPictureMapper.delete(id);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return commentPictureMapper.deleteBatch(ids);
    }

    @Override
    public int deleteByCommentId(Integer commentId) {
        return commentPictureMapper.deleteByCommentId(commentId);
    }
}
