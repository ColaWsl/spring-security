package com.wangsl.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;


    private Integer enabled;

    private static final long serialVersionUID = 1L;
}
