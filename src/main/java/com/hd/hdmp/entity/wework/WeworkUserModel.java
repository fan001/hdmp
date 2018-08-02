package com.hd.hdmp.entity.wework;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table(name = "gryx_wework_user")
public class WeworkUserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbid;
    @Column
    private String userid;
    @Column
    private String name;
    @Column(name="password")
    private String password;    //
    @Column
    private String english_name;
    @Column
    private String mobile;
    @Transient
    @JsonIgnore
    @Column(insertable=false)
    private String[] order;

    private String depart;

    @Transient
    private String[] department;

    @Column(name="department")
    private String department_;
    @Column
    private String position;
    @Column
    private String gender;
    @Column
    private String email;
    @Column
    private String telephone;
    @Column
    private String isleader;
    @Column
    private String avatar;
    @Transient
    @JsonIgnore
    @Column(insertable=false)
    private String enable;
    @Column
    private String status;
    @Transient
    @JsonIgnore
    @Column(insertable=false)
    private String extattr;
    @JsonIgnore
    private Long staffid;
    @Transient
    @JsonIgnore
    @Column(insertable=false)
    private String hide_mobile;
    @Transient
    @JsonIgnore
    @Column(insertable=false)
    private String[] department_position;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date update_date;
    @Column
    private String qr_code;


    public void setDepartment_(String[] department){
        String str = Arrays.toString(department);
        this.department_ = str.substring(1,str.length()-1);
    }

    public String getDepartment_(){
        return department_;
    }

}
