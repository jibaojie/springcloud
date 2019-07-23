package com.example.springcloud.entity.auth2;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jbj
 * @create 2019-05-13 12:41
 */
@Data
@EqualsAndHashCode
public class Permission {

    private Integer id;

    private String permName;

    private String permTag;

}
