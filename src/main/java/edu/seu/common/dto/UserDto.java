package edu.seu.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull(message = "查无此用户")
    private Integer id;
    private String username;
    private String password;
}
