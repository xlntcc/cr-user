package com.cr.user.dto.resp;

import lombok.Data;

/**
 * 更新用户响应
 */
@Data
public class UserUpdateResp {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

    /** 更新时间 */
    private String updatedAt;
}
