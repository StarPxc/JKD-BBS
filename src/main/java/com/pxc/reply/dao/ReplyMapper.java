package com.pxc.reply.dao;

import com.pxc.reply.bean.Reply;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ReplyMapper {
    /**
     * 通过主键删除记录
     * @param id id
     * @return 删除成功返回1，失败返回0
     */
    int deleteByPrimaryKey(Integer id);
    /**
     * 通过postId删除记录
     * @param pId 帖子id
     * @return 删除成功返回1，失败返回0
     */
    int deleteByPostId(Integer pId);


    /**
     * 根据reply对象有选择的插入数据
     * @param record reply对象
     * @return 插入成功返回1，失败返回0
     */
    int insertSelective(Reply record);

    /**
     * 根据主键查找记录
     * @param id id
     * @return reply对象
     */
    Reply selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录
     * @param record 对象
     * @return 更新成功返回1，失败返回0
     */

    int updateByPrimaryKeySelective(Reply record);

    /**
     * 根据post的id查询replay集合对象
     * @param id post的id
     * @return reply对象
     */

    List<Reply> selectReplyByPostId(Integer id);

    /**
     * 得到罪行的三条回复
     * @return 回复集合
     */
    List<Reply> selectLastedItem();
}