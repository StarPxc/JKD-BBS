package com.pxc.post.service;

import com.pxc.post.bean.Post;
import com.pxc.post.dao.PostMapper;
import com.pxc.reply.dao.ReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by PXC on 2017/6/20.
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper postMapper;
    @Autowired
    ReplyMapper replyMapper;

    @Override
    public List<Post> findAllPosts(int pageNum,int size) {
        return postMapper.selectAllPosts(pageNum,size);
    }

    @Override
    public List<Post> findHotPosts() {
        return postMapper.selectHostPosts();
    }

    @Override
    public List<Post> searchPosts(String postTitle) {
        return postMapper.searchPosts(postTitle);
    }

    @Override
    @Transactional
    public boolean deletePost(int id) {
        if(postMapper.deleteByPrimaryKey(id)>0){
        replyMapper.deleteByPostId(id);
            return  true;
        }else {
            return false;
        }

    }

    @Override
    public Post findPostWithUserAndReplyById(int id) {
        return postMapper.selectPostById(id);
    }

    @Override
    @Transactional
    public boolean createPost(Post post) {
        if(postMapper.insertSelective(post)>0){
            return  true;
        }else {
            return false;
        }

    }

    @Override
    public boolean update(Post post) {
        if(postMapper.updateByPrimaryKeySelective(post)>0){
            return  true;
        }else {
            return false;
        }
    }

    @Override
    public int CountPostsNum() {
        return postMapper.selectAllPostsNum();
    }


}
