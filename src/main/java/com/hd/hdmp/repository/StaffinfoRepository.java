package com.hd.hdmp.repository;

import com.hd.hdmp.entity.StaffinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanzhenxing
 * @create 2018/6/6 3:31 PM
 */
public interface StaffinfoRepository extends JpaRepository<StaffinfoEntity,Integer>,UserRepository {

    public StaffinfoEntity findByUsername(String username);
}
