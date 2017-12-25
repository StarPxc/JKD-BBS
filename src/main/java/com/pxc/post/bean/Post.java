package com.pxc.post.bean;

import com.pxc.reply.bean.Reply;

import com.pxc.user.bean.User;

import java.util.Date;
import java.util.List;

public class Post {
    private Integer id;//id

    private String postTitle;//帖子标题

    private Integer postViewCount;//帖子访问总数

    private Integer postReplyCount;//帖子回复总数

    private Date postCreateTime;//帖子创建时间

    private Date postUpdateTime;//帖子更新时间

    private Integer userId;//帖子所属的用户id

    private String postContent;//帖子的内筒

    private List<Reply> replies;//帖子的回复对象集合

    private User user;//发布帖子的用户


    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public Integer getPostViewCount() {
        return postViewCount;
    }

    public void setPostViewCount(Integer postViewCount) {
        this.postViewCount = postViewCount;
    }

    public Integer getPostReplyCount() {
        return postReplyCount;
    }

    public void setPostReplyCount(Integer postReplyCount) {
        this.postReplyCount = postReplyCount;
    }

    public Date getPostCreateTime() {
        return postCreateTime;
    }

    public void setPostCreateTime(Date postCreateTime) {
        this.postCreateTime = postCreateTime;
    }

    public Date getPostUpdateTime() {
        return postUpdateTime;
    }

    public void setPostUpdateTime(Date postUpdateTime) {
        this.postUpdateTime = postUpdateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPostContent() {
        return postContent;
    }


    public void setPostContent(String postContent) {
        this.postContent = postContent == null ? null : postContent.trim();
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postViewCount=" + postViewCount +
                ", postReplayCount=" + postReplyCount +
                ", postCreateTime=" + postCreateTime +
                ", postUpdateTime=" + postUpdateTime +
                ", userId=" + userId +
                ", postContent='" + postContent + '\'' +
                ", replays=" + replies +
                ", user=" + user +
                '}';
    }
}