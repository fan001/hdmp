package com.hd.hdmp.entity.wework;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gryx_wework_token")
public class WeworkTokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String accountid;
    @Column
    private String agentid;
    @Column
    private String token;
    @Column
    private Long time_version;
    @Column
    private String ticket;
}