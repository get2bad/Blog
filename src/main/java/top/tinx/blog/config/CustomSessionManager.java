package top.tinx.blog.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 创建人: Wills
 * 创建时间：2019/8/19 18:17
 * 描述: 前后端分离才用得到！！！
 */
@Configuration
public class CustomSessionManager extends DefaultWebSessionManager {

    private final String AUTHORIZATION = "token";

    public CustomSessionManager(){
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取前端传送的SessionId
        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if(!StringUtils.isEmpty(sessionId)){
            //将SessionId的Source发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            //将SessionId发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionId);
            //将SessionId验证成功发送到前端
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return sessionId;
        }else{
            return super.getSessionId(request, response);
        }
    }
}
