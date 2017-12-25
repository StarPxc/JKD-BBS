package com.pxc.user.dao;

import com.pxc.post.bean.Post;
import com.pxc.user.bean.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    /**
     * 根据主键删除记录
     * @param id id
     * @return 删除陈宫返回1，失败返回0
     */
    int deleteByPrimaryKey(Integer id);


    /**
     * 有选择的插入数据
     * @param record user对象
     * @return 插入成功但会1，失败返回0
     */
    int insertSelective(User record);

    /**
     * 根据主键查找对象
     * @param id id
     * @return 用户对象
     */

    User selectByPrimaryKey(Integer id);

    /**
     * 有选择的更新数据
     * @param record user对象
     * @return 更新成功返回1，失败返回0
     */

    int updateByPrimaryKeySelective(User record);

    /**
     * 根据用户名和密码查找对象
     * @param username 用户名
     * @param password 密码
     * @return 查找成功返回uer对象，是啊比返回null
     */

    User selectUserByUsernameAndPassword(String username,String password);



    User selectUserWithLastPostsById(Integer id);
}