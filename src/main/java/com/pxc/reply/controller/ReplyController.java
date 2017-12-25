package com.pxc.reply.controller;

import com.pxc.reply.bean.Reply;
import com.pxc.reply.dao.ReplyMapper;
import com.pxc.reply.service.ReplyService;
import com.pxc.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by PXC on 2017/6/27.
 */
@Controller
@RequestMapping("reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    /**
      *@Author Ethan
      *@Date 2017/6/28 18:26
      *@Description 创建帖子
      */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String postID, String replyContent, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String username=user.getUsername();
        int id=user.getId();
        Reply reply = new Reply();
        reply.setReplyUserId(user.getId() + "");
        reply.setUser(user);
        reply.setReplyUserName(username);
        reply.setReplyContent(replyContent);
        reply.setReplyCreateTime(new Date());
        reply.setReplyPostId(Integer.parseInt(postID));
       if(replyService.create(reply)) {

           return "success";
       }else {

           return "false";
       }

    }
}
