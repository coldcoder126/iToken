package com.coldcoder.iToken.service.sso.mapper;


import com.coldcoder.iToken.common.domain.TbSysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

@Repository
public interface TbSysUserMapper extends MyMapper<TbSysUser> {
}