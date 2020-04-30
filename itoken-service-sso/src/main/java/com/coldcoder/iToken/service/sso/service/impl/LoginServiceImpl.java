package com.coldcoder.iToken.service.sso.service.impl;

import com.coldcoder.iToken.common.domain.TbSysUser;
import com.coldcoder.iToken.common.utils.MapperUtils;
import com.coldcoder.iToken.service.sso.mapper.TbSysUserMapper;
import com.coldcoder.iToken.service.sso.service.LoginService;

import com.coldcoder.iToken.service.sso.service.consumer.RedisCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/25 20:38
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private TbSysUserMapper tbSysUserMapper;

    @Autowired
    private RedisCacheService redisService;
    /**
     * 先从redis中取数据，没有数据再从db中拿数据放入redis
     * @param loginCode
     * @param plantPassword
     * @return
     */
    @Override
    @ResponseBody
    public TbSysUser login(String loginCode, String plantPassword) {
        TbSysUser tbSysUser = new TbSysUser();
        System.out.println(loginCode);
        String json = redisService.get(loginCode);
        //如果缓存中没数据，就从数据库中取数据放入Redis
        if(json==null){
            Example example = new Example(TbSysUser.class);
            example.createCriteria().andEqualTo("loginCode",loginCode);
            tbSysUser = tbSysUserMapper.selectOneByExample(example);
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if(tbSysUser != null &&  password.equals(tbSysUser.getPassword())) {
                //如果加密之后的MD5密码和数据库中的密码一样，就将用户数据放入Redis并返回用户的信息
                try {
                    redisService.put(loginCode, MapperUtils.obj2json(tbSysUser), 60 * 60 * 24);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return tbSysUser;
            }
            else
                return null;
        }else {     //如果缓存中有数据
                try {
                    tbSysUser = MapperUtils.json2pojo(json, TbSysUser.class);
                } catch (Exception e) {
                   logger.warn("触发熔断：{}",e.getMessage());
                }
        }



        return tbSysUser;
    }
}
