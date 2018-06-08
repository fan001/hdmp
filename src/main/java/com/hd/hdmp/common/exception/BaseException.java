package com.hd.hdmp.common.exception;

import lombok.Data;

/**
 * @author fanzhenxing
 * @create 2018/5/29 5:14 PM
 */
@Data
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    private String msg;
    private int code = 500;

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public BaseException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }


}
