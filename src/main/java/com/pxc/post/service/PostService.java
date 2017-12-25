package com.pxc.post.service;

import com.pxc.post.bean.Post;

import java.util.List;

/**
 * Created by PXC on 2017/6/20.
 */
public interface PostService {

    /**
     * 通过ID关联查询到post对象
     * @param id id
     * @return post对象
     */
    Post findPostWithUserAndReplyById(int id);

    /**
     * 创建帖子
     * @param post 帖子对象
     * @return 创建成功返回true，失败返回false
     */
    boolean createPost(Post post);

    /**
     * 更新帖子
     * @param post 帖子对象
     * @return 更新成功返回true，失败返回false
     */
    boolean update(Post post);

    /**
     * 得到post总的个数
     * @return post总记录数
     */
    int CountPostsNum();

    /**
     * 得到所有Post对象（分页）
     * @param pageNum 当前页数*显示数目的大小
     * @param size 显示数目的大小
     * @return post集合对象
     */
    List<Post> findAllPosts(int pageNum, int size);

    /**
     * 查找热贴
     * @return 热帖集合
     */
    List<Post> findHotPosts();

    /**
     * 模糊查询查找帖子
     * @return 对象集合
     */
    List<Post> searchPosts(String postTitle);

    /**
     * 删除帖子
     * @param id
     * @return 删除成功返回true失败返回false
     */
    boolean deletePost(int id);
}
