package com.hd.hdmp.common.annotation;

import java.lang.annotation.*;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:21 AM
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
