package com.softtech.graduationproject.usr.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class UsrUserSaveDto {

    private String username;
    private String password;
    private String name;
    private String surname;

}
