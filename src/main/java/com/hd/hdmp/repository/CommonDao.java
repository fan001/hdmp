package com.hd.hdmp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author fanzhenxing
 * @create 2018/7/7 9:21 PM
 */
@NoRepositoryBean
public interface CommonDao<E,ID extends Serializable>
        extends JpaRepository<E,ID>,JpaSpecificationExecutor<E> {

}
