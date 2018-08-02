package com.hd.hdmp.repository.wework;

import com.hd.hdmp.entity.wework.WeworkUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeworkUserModelRepository extends JpaRepository<WeworkUserModel,Long> {
    WeworkUserModel findByName(String name);
    WeworkUserModel findByUserid(String userid);
    List<WeworkUserModel> findByDepart(String depart);
    WeworkUserModel findByMobile(String mobile);
}