package com.hd.hdmp.common.aspect;

import com.google.gson.Gson;
import com.hd.hdmp.common.annotation.SysLog;
import com.hd.hdmp.common.util.IPUtils;
import com.hd.hdmp.entity.HdmpSysLog;
import com.hd.hdmp.service.SysLogService;
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
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:22 AM
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    SysLogService sysLongService;

    @Pointcut("@annotation(com.hd.hdmp.common.annotation.SysLog)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        Long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        Long endTime = System.currentTimeMillis();
        saveSysLog(point,endTime-beginTime);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint point,Long time){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        HdmpSysLog hdmpSysLog = new HdmpSysLog();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if(sysLog != null){
            hdmpSysLog.setOperation(sysLog.value());
        }

        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        hdmpSysLog.setMethod(methodName);
        hdmpSysLog.setClassName(className);
        Object[] args = point.getArgs();
        try {
            String params = new Gson().toJson(args[0]);
            hdmpSysLog.setParam(params);
        } catch (Exception e) {

        }

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        hdmpSysLog.setIp(IPUtils.getIpAddr(request));
        hdmpSysLog.setExeTime(time);
        hdmpSysLog.setCreateDate(new Date());
        sysLongService.saveSysLog(hdmpSysLog);
    }

}
