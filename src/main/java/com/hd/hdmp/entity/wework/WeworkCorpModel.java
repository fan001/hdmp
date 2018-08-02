package com.hd.hdmp.entity.wework;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "gryx_wework_qywx")
public class WeworkCorpModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String accountid;
    @Column
    private String corpid;
    @Column
    private String secret;
    @Column
    private String mch_id;
    @Column
    private String welcome;
    @Column
    private String grgsbh;
    @Column
    private String city;
    @Column
    private String isdel;
    @Column
    private String create_by;
    @Column
    private Date create_date;
    @Column
    private String update_by;
    @Column
    private Date update_date;
    @Column
    private String paykey;
}