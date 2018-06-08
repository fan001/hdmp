package com.hd.hdmp.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:30 PM
 */
@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int SUCCESS = 0;

    public static final int FAIL = 1;
    public static final int TOKEN_ERROR_CODE = 40101;
    public static final int TOKEN_FORBIDDEN_CODE = 40301;

    public static final int NO_PERMISSION = 2;

    private String msg = "success";

    private int code = SUCCESS;

    private long totalCount = 0;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

}
