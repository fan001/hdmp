package com.hd.hdmp.repository.wework;

import com.hd.hdmp.entity.wework.WeworkCorpModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeworkCorpModelRepository extends JpaRepository<WeworkCorpModel,Long> {
    public WeworkCorpModel findByAccountid(String accountid);
}