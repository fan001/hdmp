package com.hd.hdmp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:46 PM
 */

@Data
@AllArgsConstructor
public class ModuleTree extends TreeNode {
    private String moduleName;
    private String href;
    private String icon;
    private String type;
    private Integer orderNum;
    private String description;
    private String path;
    private Date createTime;
    public ModuleTree(){

    }

}
