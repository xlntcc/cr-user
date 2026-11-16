package com.cr.user.dto.resp;

import lombok.Data;

/**
 * 创建用户响应
 */
@Data
public class UserCreateResp {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

    /** 创建时间 */
    private String createdAt;
}
