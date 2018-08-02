package com.hd.hdmp.repository.wework;

import com.hd.hdmp.entity.wework.WeworkTokenModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeworkTokenModelRepository extends JpaRepository<WeworkTokenModel,Long> {
    public WeworkTokenModel findByAccountidAndAgentid(String accountid, String agentid);
}