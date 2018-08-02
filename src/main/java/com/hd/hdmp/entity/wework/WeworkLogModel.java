package com.hd.hdmp.entity.wework;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "gryx_wework_log")
public class WeworkLogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String accountid;
    @Column
    private String agentid;
    @Column
    private String cznr;
    @Column
    private String bz;
    @Column
    private String czjg;
    @Column
    private Date create_date;
}