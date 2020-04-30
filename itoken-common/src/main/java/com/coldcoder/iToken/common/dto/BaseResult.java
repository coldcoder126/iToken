package com.coldcoder.iToken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * 全部接口的请求返回遵循统一的返回格式，包含"result","data","cursor","success","error"等部分
 * result:包括两种状态：""ok和"not_ok"不可为空
 * data:返回具体的数据，可以为空，默认为Object，存在数据时不一定为Object，有可能会是Array
 * cursor：返回所包含数据，可以为空，默认为Object，结构固定
 * success：成功返回信息，可以为空，默认为空字符，结构固定
 * error：错误返回信息，可以为空，默认空Array，结构固定
 *
 * offset：Integer非必填，default=0;
 * limit:Integer 非必填，default=10；
 */

@Data
public class BaseResult implements Serializable {
    private String result;
    private Object Data;
    private String success;
    private Cursor cursor;
    private List<Error> errors;

    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";

    public static BaseResult ok(){
        return createResult(RESULT_OK,null,"成功操作",null,null);
    }

    public static BaseResult ok(Object data){
        return createResult(RESULT_OK,data,"成功操作",null,null);
    }

    public static BaseResult notOk(List<BaseResult.Error> errors){
        return createResult(RESULT_NOT_OK,null,"",null,errors);
    }

    private static BaseResult createResult(String result,Object data,String success,Cursor cursor,List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setSuccess(success);
        baseResult.setCursor(cursor);
        baseResult.setErrors(errors);

        return baseResult;
    }

    /**
     * cursor n.光标
     */
    @Data
    public static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String message;
    }
}
