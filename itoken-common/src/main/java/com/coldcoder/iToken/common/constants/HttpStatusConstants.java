package com.coldcoder.iToken.common.constants;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/21 12:51
 * @Version: 1.0
 */
public enum  HttpStatusConstants {
    BAD_GATEWAY(502,"从上游服务器接收到无效对象");

    private int status;
    private String content;

    private HttpStatusConstants(int status,String content){
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
