package com.example.springcloud.entity.auth2;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jbj
 * @create 2019-05-13 12:01
 */
@Data
@EqualsAndHashCode
public class Role {

    /**
     * int(10) NOT NULL,
     */
    private Integer id;

    /**
     * varchar(50) DEFAULT NULL,
     */
    private String roleName;

    /**
     * varchar(50) DEFAULT NULL,
     */
    private String roleDesc;

}
