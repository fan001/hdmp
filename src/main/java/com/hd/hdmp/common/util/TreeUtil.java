package com.hd.hdmp.common.util;

import com.google.common.collect.Lists;
import com.hd.hdmp.vo.TreeNode;

import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 8:27 PM
 */
public class TreeUtil {


    /**
     * 创造树形结构
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Integer root) {
        List<T> tress = Lists.newArrayList();
        for (T treeNode : treeNodes) {
            if (root.intValue() == treeNode.getParentId().intValue()) {
                tress.add(findChildren(treeNode, treeNodes));
            }
        }
        return tress;
    }


    /**
     * 递归构建树形结构
     * @param treeNode
     * @param treeNodes
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (it.getParentId().intValue() == treeNode.getId().intValue()) {
                treeNode.add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}
