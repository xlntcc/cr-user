package com.cr.user.dto.resp;

import lombok.Data;

/**
 * 用户登录响应
 */
@Data
public class UserLoginResp {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录时间 */
    private String loginTime;
}
