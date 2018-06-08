package com.hd.hdmp.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author fanzhenxing
 * @create 2018/6/8 1:39 PM
 */
@Data
@AllArgsConstructor
public class ShiroUser implements Serializable {
    private Integer id;
    private String username;
    private String name;

    private Set<String> roles;
    private Set<String> urls;

}
