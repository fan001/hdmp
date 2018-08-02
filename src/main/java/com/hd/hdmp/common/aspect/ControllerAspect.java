package com.hd.hdmp.common.aspect;

import com.google.gson.Gson;
import com.hd.hdmp.common.bean.ResultBean;
import com.hd.hdmp.common.util.IPUtils;
import com.hd.hdmp.entity.HdmpSysLog;
import com.hd.hdmp.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author fanzhenxing
 * @create 2018/6/5 2:08 PM
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Autowired
    SysLogService sysLogService;

    @Pointcut("execution(public com.hd.hdmp.common.bean.ResultBean  com.hd.hdmp.controller..*.*(..))" +
            "&&@within(org.springframework.web.bind.annotation.RestController)")
    public void performance() {

    }


    @Around("performance()")
    public Object handlerController(ProceedingJoinPoint point) throws Throwable {
        Long start = System.currentTimeMillis();
        System.out.println("aa");
        ResultBean<?> resultBean;
        HdmpSysLog sysLog = new HdmpSysLog();
        sysLog.setIsSuccess(true);
        Date date = new Date();
        sysLog.setCreateDate(date);

        try {
            resultBean = (ResultBean<?>) point.proceed();

        } catch (Throwable e) {
            log.error("we com across a exception");
            log.error(e.getMessage(), e);
            sysLog.setException(e.toString() + ":" + e.getMessage());
            throw e;
        } finally {
            Long end = System.currentTimeMillis();
            sysLog.setIsSuccess(false);
            sysLog.setExeTime(end - start);
            saveLog(point,sysLog);

        }
        return resultBean;
    }

    /**
     * 保存日志
     * @param point
     * @param sysLog
     */
    private void saveLog(ProceedingJoinPoint point, HdmpSysLog sysLog) {
        String className = point.getTarget().getClass().getName();
        MethodSignature signature = (MethodSignature) point.getSignature();
        String methodName = signature.getName();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtils.getIpAddr(request);
        String uri = request.getRequestURI();
        Object[] args = point.getArgs();
        String params = "";
        try {
            params = new Gson().toJson(args);
        } catch (Exception ex) {
            log.error("转化方法失败", ex);
        }
        sysLog.setIp(ip);
        sysLog.setParam(params);
        sysLog.setClassName(className);
        sysLog.setMethod(methodName);
        sysLog.setUri(uri);
        sysLogService.saveSysLog(sysLog);


    }


}
