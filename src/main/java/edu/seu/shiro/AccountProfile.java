package edu.seu.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录成功后，只能公开一部分账户信息
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;
    private String username;
    private String avatar;
    private String email;
}
