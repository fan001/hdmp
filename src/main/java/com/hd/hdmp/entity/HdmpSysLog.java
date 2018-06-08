package com.hd.hdmp.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:28 AM
 */
@Entity
@Data

public class HdmpSysLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    private String username;
    private String operation;
    private String className;
    private String method;
    private String param;
    private Long exeTime;
    private String ip;
    private Date createDate;
    private Boolean isSuccess;
    private String uri;
    private String exception;

}
