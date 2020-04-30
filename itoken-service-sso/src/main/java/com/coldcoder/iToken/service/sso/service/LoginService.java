package com.coldcoder.iToken.service.sso.service;

import com.coldcoder.iToken.common.domain.TbSysUser;

/**
 * @Author: DX
 * @Description: 登录业务
 * @Date: 2020/2/25 16:09
 * @Version: 1.0
 */
public interface LoginService {
    public TbSysUser login(String loginCode,String plantPassword);
}
