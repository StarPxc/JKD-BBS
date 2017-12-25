package com.pxc.user.service;

import com.pxc.post.bean.Post;
import com.pxc.user.bean.User;

import java.util.List;

/**
 * Created by PXC on 2017/6/24.
 */
public interface UserService {
    /**
     * 根据用户名和密码查找对象
     * @param username 用户名
     * @param password 密码
     * @return 查找成功返回uer对象，是啊比返回null
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 创建用户
     * @param user 用户对象
     * @return 创建成功返回true，失败返回false
     */
    boolean createUser(User user);

    /**
     * 更新对象
     * @param user 用户对象
     * @return 更新成功返回true失败返回dalse
     */
    boolean update(User user);


    User findUserWithLastPostsById(Integer id);
}
