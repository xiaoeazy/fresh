package com.fresh.manager.mapper.shop;

import com.fresh.manager.mapper.BaseMapper;
import com.fresh.manager.pojo.shop.CommentPictureEntity;

/**
 * 评价图片Dao
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-29 14:45:55
 */
public interface CommentPictureMapper extends BaseMapper<CommentPictureEntity> {
    /**
     * 根据commentId删除
     *
     * @param commentId
     * @return
     */
    int deleteByCommentId(Integer commentId);
}
