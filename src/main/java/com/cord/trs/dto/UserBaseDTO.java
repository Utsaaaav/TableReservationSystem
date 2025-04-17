package com.cord.trs.dto;

import lombok.*;

@Getter
@Setter
@Builder

public class UserBaseDTO {

    private long  id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

}
