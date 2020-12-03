package com.learn.protocol.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.learn.protocol.error.ErrorCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code = ErrorCode.NO_ERROR.getCode();

    private String msg = ErrorCode.NO_ERROR.getMessage();

    private String timestamp;

    private long count;

    @JsonInclude(Include.NON_NULL)
    private T data;


    public ApiResponse() {
        super();
    }

    public ApiResponse(T t) {
        this.data = t;
    }

    /**
     * 针对layUI返回数据格式封装数据
     * @param t
     */
    public ApiResponse(T t, long count) {
        this.data = t;
        this.count = count;
    }

    public ApiResponse(ErrorCode protocolErrorCode) {
        this.code = protocolErrorCode.getCode();
        this.msg = protocolErrorCode.getMessage();
    }

    public ApiResponse(ErrorCode protocolErrorCode, String msg) {
        this.code = protocolErrorCode.getCode();
        this.msg = msg;
    }

}
