package com.softtech.graduationproject.usr.entity;

import com.softtech.graduationproject.gen.entity.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USR_USER")
@Data
public class UsrUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Column(name = "PASSWORD", length = 30, nullable = false)
    private String password;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 30, nullable = false)
    private String surname;
}
