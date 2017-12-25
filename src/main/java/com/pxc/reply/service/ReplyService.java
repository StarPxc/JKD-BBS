package com.pxc.reply.service;

import com.pxc.reply.bean.Reply;

import java.util.List;

/**
 * Created by PXC on 2017/6/23.
 */
public interface ReplyService {
    /**
     * 以post的id获得所关联的reply集合对象
     * @param id post的id
     * @return reply集合对象
     */
    List<Reply> findReplyByPostId(int id);

    /**
     * 增加回复
     * @param reply reply对象
     * @return 增加成功返回true，失败返回false
     */
    boolean create(Reply reply);

    /**
     * 得到最新的三条回复
     * @return
     */
    List<Reply> findLastedItem();
}
