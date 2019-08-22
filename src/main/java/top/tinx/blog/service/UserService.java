package top.tinx.blog.service;

import top.tinx.blog.bean.Login;
import top.tinx.blog.bean.User;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 17:38
 * 描述:
 */
public interface UserService {

    /**
     * 根据用户名查找所有用户的基本信息
     * @param userName
     * @return
     */
    public User findAllUserInfoByUserName(String userName);

    /**
     * 根据用户ID查找用户基本信息
     * @param userId
     * @return
     */
    public User findUserByUserId(int userId);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return
     */
    public User findSimpleUserByUserName(String username);

    /**
     * 登陆
     * @return
     */
    public User findUserByUserNameAndPassword(String userName,String password);

    /**
     * 修改登陆的IP地址
     * @param ip
     * @param user_id
     */
    public void updateSignInIP(String ip,String user_id);
}