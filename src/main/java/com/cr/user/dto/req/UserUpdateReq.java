package com.cr.user.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 更新用户请求
 */
@Data
public class UserUpdateReq {

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 手机号 */
    private String phone;

    /** 状态 0-禁用 1-启用 */
    private Integer status;
}
