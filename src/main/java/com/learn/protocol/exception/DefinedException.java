package com.learn.protocol.exception;

import com.learn.protocol.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
   * @author: guoLiang
   * @date：2018-09-29 03:46:03
   * @类描述：
   */
public class DefinedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());

    private String msg;
    private int code = ErrorCode.BAD_REQUEST.getCode();
    
    public DefinedException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public DefinedException(String msg, Throwable e) {
		super(msg, e);
		logger.error(msg+ e);
		this.msg = msg;
	}
	
	public DefinedException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public DefinedException(ErrorCode ed) {
		super(ed.getMessage());
		this.msg = ed.getMessage();
		this.code = ed.getCode();
	}

	public DefinedException(ErrorCode ed,String msg) {
		super(msg);
		this.msg = msg;
		this.code = ed.getCode();
	}

	public DefinedException(String msg, int code, Throwable e) {
		super(msg, e);
		logger.error(msg+ e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
