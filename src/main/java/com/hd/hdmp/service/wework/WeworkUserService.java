package com.hd.hdmp.service.wework;

import com.hd.hdmp.common.util.wework.CommonUtil;
import com.hd.hdmp.entity.wework.WeworkTokenModel;
import com.hd.hdmp.entity.wework.WeworkUserModel;
import com.hd.hdmp.repository.wework.WeworkUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class WeworkUserService {
    @Autowired
    WeworkUserModelRepository weworkUserModelRepository;
    @Autowired
    WeworkTokenService weworkTokenService;

    //保存当前用户
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public Boolean saveWeworkUserModel(WeworkUserModel WeworkUserModel){
        weworkUserModelRepository.save(WeworkUserModel);
        return true;
    }

    //保存所有用户
    @Transactional(rollbackFor = {RuntimeException.class,Exception.class})
    public Boolean saveWeworkUserModels(List<WeworkUserModel> list){
        weworkUserModelRepository.saveAll(list);
        return true;
    }

    //根据用户名称获取用户信息
    public WeworkUserModel findByName(String name){
        WeworkUserModel weworkUserModel = weworkUserModelRepository.findByName(name);
        return weworkUserModel;
    }

    //根据code获取userid获取用户信息
    public WeworkUserModel findByCode(String accountid, String agentid, String code){
        //获取应用的token
        WeworkTokenModel weworkTokenModel = weworkTokenService.doTokenService(accountid,agentid);
        System.out.println("findbycode:::"+weworkTokenModel.getToken());
        //通过token和code获取userid
        String userid = CommonUtil.getUserIDFromOauth2(weworkTokenModel.getToken(), code);
        System.out.println("WeworkUserService::userid::"+userid);
        //通过userid获取用户信息
        WeworkUserModel weworkUserModel = weworkUserModelRepository.findByUserid(userid);
        return weworkUserModel;

    }

    //根据用户id获取用户信息
    public WeworkUserModel findByUserid(String userid){
        WeworkUserModel weworkUserModel = weworkUserModelRepository.findByUserid(userid);
        return weworkUserModel;
    }

    //获取部门下所有用户并保存
    public Boolean saveAllUser(String accountid){
        //获取通讯录token对象，参数 agenti为空即可
        WeworkTokenModel weworkTokenModel = weworkTokenService.doTokenService(accountid,"");
        String access_token = weworkTokenModel.getToken();
        //获取部门id(获取list.get(0)为公司总部门)
        String departmentid = CommonUtil.getDepartmentid(access_token,"");
        System.out.println("departmentid::"+departmentid);
        //根据部门获取所有用户
        List<WeworkUserModel> list = CommonUtil.getUserByDepartmentId(access_token,departmentid);
        list.forEach(
            model->{
                model.setDepartment_((model.getDepartment()));
                model.setDepart(model.getDepartment_());
                System.out.println("userid::"+model.getUserid()+",,,department==:::"+model.getDepartment()[0]+":::"+model.getDepartment_());

                WeworkUserModel weworkUserModel = weworkUserModelRepository.findByUserid(model.getUserid());
                if(weworkUserModel!=null){
                    model.setPassword(weworkUserModel.getPassword());
                    model.setDbid(weworkUserModel.getDbid());
                    model.setCreate_date(weworkUserModel.getCreate_date());
                    model.setStaffid(weworkUserModel.getStaffid());
                    model.setUpdate_date(new Date());
                }else{
                    model.setPassword("123");
                    model.setCreate_date(new Date());
                    model.setUpdate_date(new Date());
                }

            });
        //保存所有用户
        return saveWeworkUserModels(list);
    }

    //根据部门id获取部门下属员工
    public List<WeworkUserModel> getUserList(String department){
        return weworkUserModelRepository.findByDepart(department);
    }
}