package com.learn.model;

import jdk.nashorn.internal.objects.annotations.Property;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Alias("sysUser")
@Table(name = "sys_user")
@Entity
public class UserModel implements Serializable {
    @Id
    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "sex")
    private String sex;
}
