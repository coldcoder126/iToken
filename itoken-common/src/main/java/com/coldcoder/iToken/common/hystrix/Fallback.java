package com.coldcoder.iToken.common.hystrix;

import com.coldcoder.iToken.common.constants.HttpStatusConstants;
import com.coldcoder.iToken.common.dto.BaseResult;
import com.coldcoder.iToken.common.utils.MapperUtils;
import com.google.common.collect.Lists;

/**
 * @Author: DX
 * @Description: 通用的熔断方法
 * @Date: 2020/2/25 20:53
 * @Version: 1.0
 */
public class Fallback  {

    public static String badGateway(){
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(
                String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()),
                HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
