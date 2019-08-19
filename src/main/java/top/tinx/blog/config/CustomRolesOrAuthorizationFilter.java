package top.tinx.blog.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:19
 * 描述: 验证权限是否有，原有shiro框架如果入到role[admin,root]时要两者一起满足才能访问，但是我们的需求是两者有
 * 其一就可以访问，所以在此改变方法内容!
 */
@Configuration
public class CustomRolesOrAuthorizationFilter extends AuthorizationFilter {

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);
        String[] rolesArray = (String[])((String[])mappedValue);
        if (rolesArray != null && rolesArray.length != 0) {
            Set<String> roles = CollectionUtils.asSet(rolesArray);
            for (String role : roles){
                if(subject.hasRole(role)){
                    return true;
                }
            }
            return false;
        } else {
            return true;
        }
    }
}
