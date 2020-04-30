package com.coldcoder.iToken.service.sso.controller;

import com.coldcoder.iToken.common.domain.TbSysUser;
import com.coldcoder.iToken.common.utils.CookieUtils;
import com.coldcoder.iToken.common.utils.MapperUtils;
import com.coldcoder.iToken.service.redis.service.RedisService;
import com.coldcoder.iToken.service.sso.service.LoginService;
import com.coldcoder.iToken.service.sso.service.consumer.RedisCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/26 16:13
 * @Version: 1.0
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;



    @Autowired
    RedisCacheService redisCacheService;



    /**
     * 跳转登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(@RequestParam(required = false) String url,HttpServletRequest request,Model model){
        //查看Cookie中是否有值来判断是否登录
        String token = CookieUtils.getCookieValue(request,"token");
        //token不为空，可能已登录，真正有没有已登录需要在redis中看。
        String loginCode = redisCacheService.get(token);
        if(StringUtils.isNotBlank(loginCode)){
            String json = redisCacheService.get(loginCode);
            if(StringUtils.isNotBlank(loginCode)){
                try {
                    TbSysUser tbSysUser = MapperUtils.json2pojo(json,TbSysUser.class);
                    //已登录
                    if(tbSysUser!=null){
                        //如果是从别的网站跳转来SSO的，验证已登录后再跳转回去
                        if(StringUtils.isNotBlank(url)) return "redirect"+url;
                    }
                    //否则将登录信息传到登录页
                    model.addAttribute("tbSysUser",tbSysUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(StringUtils.isNotBlank(url)) model.addAttribute("url",url);
        return "login";
    }

    /**
     * 登录业务
     * @param loginCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true)String loginCode,
                        @RequestParam(required = true)String password,
                        @RequestParam(required = false) String url,
                        HttpServletRequest request, HttpServletResponse response,
                        RedirectAttributes redirectAttributes){
        TbSysUser tbSysUser = loginService.login(loginCode,password);
        System.out.println(tbSysUser);
        //登录失败
        if(tbSysUser == null){
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
        }else { //登录成功
            String token = UUID.randomUUID().toString();
            String result = redisCacheService.put(token,loginCode,60*60*24);
            if(StringUtils.isNotBlank(result) && "ok".equals(result)) {
                CookieUtils.setCookie(request, response, "token", token,60*60*24);
                if(StringUtils.isNotBlank(url)) return "redirect"+url;
            }else {  //熔断处理
                redirectAttributes.addFlashAttribute("message","服务器异常，请求被熔断");
            }
        }
        return "redirect:login";

    }

    /**
     * 登出
     * @param request
     * @param response
     * @param url
     * @param model
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request,HttpServletResponse response,@RequestParam(required = false) String url,Model model){
        CookieUtils.deleteCookie(request,response,"token");
        return login(url,request,model);
    }
}
