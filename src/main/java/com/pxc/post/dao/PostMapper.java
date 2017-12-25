package com.pxc.post.dao;

import com.pxc.post.bean.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostMapper {
    /**
     * 根据主键删除记录
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据传入的对象有选择的插入数据
     * @param record 记录对象
     * @return 掺入成功返回1 失败返回0
     */
    int insertSelective(Post record);

    /**
     * 根据对象有选择的更新记录
     * @param record post对象
     * @return 更新成功返回1，失败返回0
     */
    int updateByPrimaryKeySelective(Post record);


    int updateByPrimaryKeyWithBLOBs(Post record);

    /**
     * 返回所有Post集合（分页）
     * @param pageNum 当兵前页数*显示条目大小
     * @param size 显示条目大小
     * @return post集合
     */
    List<Post> selectAllPosts(Integer pageNum,Integer size);

    /**
     * 根据id查找记录
     * @param id id
     * @return post对象
     */
    Post selectPostById(Integer id);

    /**
     * 查找数据库帖子总记录数
     * @return 总记录数
     */
    int selectAllPostsNum();

    /**
     * 查找热门帖子
     * @return 热门帖子集合
     */

    List<Post> selectHostPosts();

    /**
     * 模糊查询帖子
     * @return 帖子集合
     */
    List<Post> searchPosts(String postTitle);
}