package com.coldcoder.iToken.service.posts.service.impl;

import com.coldcoder.iToken.common.domain.BaseDomain;
import com.coldcoder.iToken.common.service.impl.BaseServiceImpl;
import com.coldcoder.iToken.service.posts.service.PostsService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.MyMapper;

/**
 * @Author: DX
 * @Description:
 * @Date: 2020/3/23 15:44
 * @Version: 1.0
 */
@Service
public class PostsServiceImpl<T extends BaseDomain,D extends MyMapper<T>> extends BaseServiceImpl<T,D> implements PostsService<T> {
}
