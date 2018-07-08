package com.hd.hdmp.repository;

import com.hd.hdmp.entity.StaffinfoEntity;

import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/7/8 2:15 PM
 */
public interface UserRepository  {
    List<StaffinfoEntity> searchBydes();
}
