package com.learn.protocol.exception;

import com.learn.protocol.error.ErrorCode;

import java.util.HashMap;

/**
 * @类描述：错误数据处理
 */
public class DefinedMap extends HashMap<String, Object> {

    private static final long serialVersionUID = 3869132562722061119L;

    public static DefinedMap error(ErrorCode errorCode) {
        DefinedMap dm = new DefinedMap();
        dm.put("code", errorCode.getCode());
        dm.put("msg", errorCode.getMessage());
        return dm;
    }

    public static DefinedMap error() {
        DefinedMap dm = new DefinedMap();
        dm.put("code", ErrorCode.INTERNAL_SERVER_ERROR.getCode());
        dm.put("msg", ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        return dm;
    }

}
