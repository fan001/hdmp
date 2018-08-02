package com.hd.hdmp.entity.task;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wework_purchase_requisition")
public class WeworkPurchaseRequisitionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String banfn;
    @Column
    private Double zprzje;
    @Column
    private Date badat;
    @Column
    private String afnam;
    @Column
    private String zstatue;
    @Column
    private String ernam;
    @Column
    private String matnr;
    @Column
    private String matxt;
    @Column
    private Double menge;
    @Column
    private String meins;
    @Column
    private Double preis;
    @Column
    private Double zzjg;
    @Column
    private String sakto;
    @Column
    private String kostl;
    @Column
    private String lifnr;
    @Column
    private String knttp;
    @Column
    private String aufnr;
    @Column
    private Double preis1;
    @Column
    private String waers;
    @Column
    private String txz01;
    @Column
    private String mobile;
}