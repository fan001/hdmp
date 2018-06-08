package com.hd.hdmp.service;

import com.google.common.collect.Lists;
import com.hd.hdmp.common.annotation.SysLog;
import com.hd.hdmp.common.util.TreeUtil;
import com.hd.hdmp.entity.HdmpModule;
import com.hd.hdmp.repository.HdmpModuleRepository;
import com.hd.hdmp.vo.ModuleTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 8:22 PM
 */
@Component
public class HdmpModuleService {
    @Autowired
    HdmpModuleRepository moduleRepository;


    public List<HdmpModule> listAllModule() {
        return null;
    }


    @SysLog
    public List<ModuleTree> listModuleTree() {

        List<HdmpModule> allModule = moduleRepository.findAll();
        List<ModuleTree> moduleTrees = Lists.newArrayList();
        allModule.forEach(e -> {
            ModuleTree node = new ModuleTree();
            BeanUtils.copyProperties(e, node);
            moduleTrees.add(node);
        });
        return TreeUtil.buildByRecursive(moduleTrees, 142);

    }
}
