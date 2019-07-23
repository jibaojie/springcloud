package com.example.springcloud.entity.auth2;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author jbj
 * @create 2019-05-13 12:02
 */
@Data
@EqualsAndHashCode
public class User {

    private Integer id;

    private String userName;

    private String realName;

    private String password;

    private Date createDate;

    private Date lastLoginTime;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;


}
