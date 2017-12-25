package com.pxc.user.bean;

import com.pxc.post.bean.Post;
import com.pxc.reply.bean.Reply;

import java.util.List;

public class User {
    private Integer id;//id

    private String username;//用户名

    private String password;//密码

    private String email;//邮箱

    private String selfIntroduction;//个人介绍

    private String headPic;//头像地址

    private int createPostCount;//创建帖子的总数

    public int getCreatePostCount() {
        return createPostCount;
    }

    public void setCreatePostCount(int createPostCount) {
        this.createPostCount = createPostCount;
    }

    private List<Reply> replies;

    private List<Post> posts;

    public List<Reply> getReplies() {
        return replies;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction == null ? null : selfIntroduction.trim();
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

}