package com.hd.hdmp.service;

import com.hd.hdmp.entity.StaffinfoEntity;
import com.hd.hdmp.repository.StaffinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fanzhenxing
 * @create 2018/6/6 3:33 PM
 */
@Component
public class StaffinfoService {

    @Autowired
    StaffinfoRepository staffinfoRepository;

    public StaffinfoEntity findByUsername(String username){

        return staffinfoRepository.findByUsername(username);
    }


    public String getOpenIdFromOauth2(String accountId,String code){
        return null;

    }
}
