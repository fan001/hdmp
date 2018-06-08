package com.hd.hdmp.controller;

import com.hd.hdmp.common.annotation.SysLog;
import com.hd.hdmp.common.bean.ResultBean;
import com.hd.hdmp.common.exception.BaseException;
import com.hd.hdmp.entity.HdmpModule;
import com.hd.hdmp.service.HdmpModuleService;
import com.hd.hdmp.vo.ModuleTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 8:22 PM
 */
@RestController
public class ModuleController extends BaseController {

    @Autowired
    HdmpModuleService moduleService;

    @ApiOperation(value = "列出所有的模块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String")})
    @GetMapping("/moduleTrees")
    public ResultBean<List<ModuleTree>> listModuleTree(HdmpModule hdmpModule, String name) {

        ResultBean<List<ModuleTree>> resultBean =
                new ResultBean<>(moduleService.listModuleTree());
        if (1 == 1) {
            throw new BaseException("测试错误");
        }
        return resultBean;

    }

    @GetMapping("/test1")
    public ResultBean testResultBean(String username) {
        return new ResultBean<>("aaaaa");

    }

    @GetMapping("/nolog")
    public String noLog() {

        if (1 == 1) {
            throw new BaseException("测试错误");
        }


        return "nolog";
    }


}
