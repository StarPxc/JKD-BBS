package com.pxc.user.service;

import com.pxc.post.bean.Post;
import com.pxc.user.bean.User;
import com.pxc.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by PXC on 2017/6/24.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user=userMapper.selectUserByUsernameAndPassword(username,password);
    return  user;
    }

    @Override
    @Transactional
    public boolean createUser(User user) {
        if( userMapper.insertSelective(user)>0){
                return  true;
        }else {
            return  false;
        }


    }

    @Override
    public boolean update(User user) {
        if( userMapper.updateByPrimaryKeySelective(user)>0){
            return  true;
        }else {
            return  false;
        }
    }

    @Override
    public User findUserWithLastPostsById(Integer id) {

        return userMapper.selectUserWithLastPostsById(id);
    }


}
