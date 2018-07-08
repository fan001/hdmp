package com.hd.hdmp.repository.impl;

import com.google.common.collect.Lists;
import com.hd.hdmp.entity.StaffinfoEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * @author fanzhenxing
 * @create 2018/7/8 2:17 PM
 */

public class StaffinfoRepositoryImpl {

    @PersistenceContext
    private EntityManager em;

    public List<StaffinfoEntity> searchBydes(){
        String sql = "select * from hd_staffinfo";
        Query query = em.createNativeQuery(sql,  StaffinfoEntity.class);
        List<Object[]> list = query.getResultList();
        return Lists.newArrayList();

    }
}
