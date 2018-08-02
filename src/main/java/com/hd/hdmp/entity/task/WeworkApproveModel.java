package com.hd.hdmp.entity.task;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "wework_approver")
public class WeworkApproveModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private Long fkdbid;
    @Column
    private String type;
    @Column(name="zrel_name")
    private String zrelName;
    @Column
    private Date approvetime;
}