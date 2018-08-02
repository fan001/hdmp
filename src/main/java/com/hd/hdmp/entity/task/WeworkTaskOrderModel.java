package com.hd.hdmp.entity.task;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wework_work_order")
public class WeworkTaskOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String aufnr;
    @Column
    private String ktext;
    @Column
    private String ernam;
    @Column
    private String status;
    @Column
    private String auart;
    @Column
    private String tplnr;
    @Column
    private String pltxt;
    @Column
    private String equnr;
    @Column
    private String eqktx;
    @Column
    private Double pkostenkgr;
    @Column
    private Date erdat;
    @Column
    private String matnr;
    @Column
    private String matxt;
    @Column
    private Double bdmng;
    @Column
    private String meins;
    @Column
    private Double verpr;
    @Column
    private Double totalprice;
    @Column
    private String mobile;
}