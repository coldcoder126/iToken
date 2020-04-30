package com.coldcoder.iToken.web.admin.interceptor;

import com.coldcoder.iToken.common.domain.TbSysUser;
import com.coldcoder.iToken.common.utils.CookieUtils;
import com.coldcoder.iToken.common.utils.MapperUtils;
import com.coldcoder.iToken.web.admin.service.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * @Author: DX
 * @Description:
 * @Date: 2020/3/11 1:10
 * @Version: 1.0
 */
public class WebAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisCacheService redisCacheService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = CookieUtils.getCookieValue(request,"token");
        //token 为空表示一定没有登录
        if(StringUtils.isBlank(token)){
            response.sendRedirect("http://localhost:8504/login?url=http:localhost:8601");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        TbSysUser tbSysUser = (TbSysUser) session.getAttribute("tbSysUser");
        if(tbSysUser != null){
            if(modelAndView != null) modelAndView.addObject("tbSysUser",tbSysUser);
        }
        //未登录状态
        else {
            String token = CookieUtils.getCookieValue(request,"token");
            if(StringUtils.isNotBlank(token)){
                String loginCode = redisCacheService.get(token);
                if(StringUtils.isNotBlank(loginCode)){
                    String json = redisCacheService.get(loginCode);
                    if(StringUtils.isNotBlank(json)){
                        //已登录状态，有登录信息
                        tbSysUser = MapperUtils.json2pojo(json,TbSysUser.class);
                        request.getSession().setAttribute("tbSysUser",tbSysUser);
                    }
                }
            }

        }
        //二次确认是否有用户信息
        if(tbSysUser == null)
            response.sendRedirect("http://localhost:8504/login?url=http:localhost:8601");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
