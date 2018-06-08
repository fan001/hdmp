package com.hd.hdmp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:41 PM
 */

@Entity
@Data
@Table(name = "hd_role_purview")
public class RolePurviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exp_id")
    private Integer expId;
    private String type;   //00 表示模块显示权限, 01 表示操作权限

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "permission_id")
    private PermissionEntity permissionEntity;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

}