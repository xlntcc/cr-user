package com.cr.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 创建用户请求
 */
@Data
public class UserCreateReq {

    /** 用户名 */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /** 密码 */
    @NotBlank(message = "密码不能为空")
    private String password;

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;
}
