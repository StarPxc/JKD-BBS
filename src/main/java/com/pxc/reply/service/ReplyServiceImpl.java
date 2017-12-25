package com.pxc.reply.service;

import com.pxc.reply.bean.Reply;
import com.pxc.reply.dao.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PXC on 2017/6/23.
 */
@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<Reply> findReplyByPostId(int id) {
        return replyMapper.selectReplyByPostId(id);
    }

    @Override
    public boolean create(Reply reply) {

        return replyMapper.insertSelective(reply)>0;
    }

    @Override
    public List<Reply> findLastedItem() {
        return replyMapper.selectLastedItem();
    }
}
