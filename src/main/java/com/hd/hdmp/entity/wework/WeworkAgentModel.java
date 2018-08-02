package com.hd.hdmp.entity.wework;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gryx_wework_agent")
public class WeworkAgentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String accountid;
    @Column
    private String corpid;
    @Column
    private String corpsecret;
    @Column
    private String agentid;
    @Column
    private String secret;
    @Column
    private String grgsbh;
    @Column
    private String city;
    @Column
    private String ym;
    @Column
    private String isdel;


}