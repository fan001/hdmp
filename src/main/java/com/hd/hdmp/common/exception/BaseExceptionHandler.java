package com.hd.hdmp.common.exception;

import com.hd.hdmp.common.bean.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author fanzhenxing
 * @create 2018/5/29 5:17 PM
 */
@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResultBean<?> handlerBaseException(BaseException e) {
        ResultBean<String> resultBean = new ResultBean();
        resultBean.setCode(ResultBean.FAIL);
        resultBean.setData(e.getMsg());
        resultBean.setMsg(e.getCode() + "");

        return resultBean;
    }

    @ExceptionHandler(Exception.class)
    public ResultBean<?> handlerException(Exception e) {
        ResultBean<String> resultBean = new ResultBean<>();
        log.error(e.getMessage(), e);
        resultBean.setData(e.getMessage());
        resultBean.setCode(ResultBean.FAIL);
        return resultBean;
    }
}
