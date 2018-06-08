package com.hd.hdmp.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:44 PM
 */
@Data
public class TreeNode {
    protected Integer id;
    protected Integer parentId;
    List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node) {
        children.add(node);
    }
}
