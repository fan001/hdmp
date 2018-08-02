package com.hd.hdmp.repository.wework;

import com.hd.hdmp.entity.wework.WeworkAgentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeworkAgentModelRepository extends JpaRepository<WeworkAgentModel,Long> {
    WeworkAgentModel findByAccountidAndAgentid(String accountid, String agentid);
    WeworkAgentModel findByAccountid(String accountid);
    WeworkAgentModel findByGrgsbh(String grgsbh);

}