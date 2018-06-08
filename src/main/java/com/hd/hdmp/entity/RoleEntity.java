package com.hd.hdmp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author fanzhenxing
 * @create 2018/5/29 7:41 PM
 */

@Entity
@Data
@Table(name = "hd_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "role_name")
    private String roleName;
    private String description;
    private String status;


    @ManyToMany
    @JoinTable(name = "hd_staffinfo_role", joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "staffinfo_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<StaffinfoEntity> userInfos;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private List<RolePurviewEntity> rolePurviewEntities;
}

