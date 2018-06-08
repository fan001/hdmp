package com.hd.hdmp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:42 PM
 */

@Entity
@Table(name = "hd_permission")
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String url;
    @Column(name = "iconcls")
    private String iconCls;

    @Column(name = "is_display")
    private Boolean isDisplay;

    @Column(name = "parent_id")
    private Integer parentId;
    private String type; // 00 表示模块显示, 01 标识操作权限

    @OneToMany(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id",referencedColumnName = "id")
    private List<RolePurviewEntity> rolePurviewEntities;
}

