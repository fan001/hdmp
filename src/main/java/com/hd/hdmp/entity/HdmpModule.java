package com.hd.hdmp.entity;

import lombok.Data;
import org.apache.shiro.dao.DataAccessException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author fanzhenxing
 * @create 2018/5/29 8:04 PM
 */
@Data
@Entity
public class HdmpModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String moduleName;
    private String href;
    private Integer parentId;
    private String icon;
    private String type;
    private Integer orderNum;
    private String description;
    private String path;
    private Date createTime;

}
