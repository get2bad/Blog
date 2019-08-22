package top.tinx.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tinx.blog.bean.Login;
import top.tinx.blog.bean.Role;
import top.tinx.blog.bean.User;
import top.tinx.blog.maaper.RoleMapper;
import top.tinx.blog.maaper.UserMapper;
import top.tinx.blog.service.UserService;

import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:38
 * 描述:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findAllUserInfoByUserName(String userName) {
        User user = userMapper.findUserByUserName(userName);
        List<Role> roleList = roleMapper.findRoleListByUserId(user.getUserId());
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public User findUserByUserId(int userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public User findSimpleUserByUserName(String username) {
        return userMapper.findUserByUserName(username);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName,String password) {
        return userMapper.findUserByUsernameAndPassword(userName,password);
    }

    @Override
    public void updateSignInIP(String ip, String user_id) {
        userMapper.updateLoginIP(ip,user_id);
    }
}
