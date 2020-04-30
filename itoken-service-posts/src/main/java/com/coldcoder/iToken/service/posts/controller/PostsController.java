package com.coldcoder.iToken.service.posts.controller;

import com.coldcoder.iToken.common.domain.TbPostsPost;
import com.coldcoder.iToken.common.dto.BaseResult;
import com.coldcoder.iToken.service.posts.service.PostsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: DX
 * @Description:
 * @Date: 2020/3/23 15:56
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "v1/posts")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @ApiOperation("文章列表接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "postGuid",value = "页码" ,required = true,dataType = "String",paramType = "path")
    )
    @RequestMapping(value = "{postsGuid}",method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postsGuid") String postsGuid){
        TbPostsPost tbPostsPost = new TbPostsPost();
        tbPostsPost.setPostGuid(postsGuid);
        TbPostsPost obj = (TbPostsPost) postsService.selectOne(tbPostsPost);
        return BaseResult.ok(obj);
    }
}
