package top.tinx.blog.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.tinx.blog.bean.Permission;
import top.tinx.blog.bean.Role;
import top.tinx.blog.bean.User;
import top.tinx.blog.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:13
 * 描述:
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 在权限验证的时候触发
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("==============正在进行授权信息验证==============");
        //获取登陆时的用户名
        User user;
        User newUser = (User) principalCollection.getPrimaryPrincipal();
        if(!StringUtils.isEmpty(newUser.getUserName())){
            user = userService.findAllUserInfoByUserName(newUser.getUserName());
            if((user ==null) ||(StringUtils.isEmpty(user.getPassword()))){
                return null;
            }
        }else{
            return null;
        }

        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        List<Role> roleList1 = user.getRoleList();
        for(Role r : roleList1){
            roleList.add(r.getAuthName());
            List<Permission> permissionList1 = r.getPermissionList();
            for (Permission p : permissionList1){
                permissionList.add(p.getName());
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roleList);
        authorizationInfo.addStringPermissions(permissionList);
        System.out.println("==============================================");
        return authorizationInfo;
    }

    /**
     * 在身份验证的时候触发
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("==============正在进行身份信息验证==============");
        //获取登陆时的用户名
        User user;
        String userName = (String) token.getPrincipal();
        if(StringUtils.isEmpty(userName)){
            return null;
        }
        user = userService.findSimpleUserByUserName(userName);
        if(user ==null){
            return null;
        }
        System.out.println("==============================================");
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
