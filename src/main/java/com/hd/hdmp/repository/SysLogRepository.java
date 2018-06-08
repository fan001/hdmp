package com.hd.hdmp.repository;

import com.hd.hdmp.common.annotation.SysLog;
import com.hd.hdmp.entity.HdmpSysLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author fanzhenxing
 * @create 2018/5/29 11:31 AM
 */
public interface SysLogRepository extends JpaRepository<HdmpSysLog,Long> {
}
