package com.cr.user.dto.resp;

import lombok.Data;

/**
 * 用户详情响应
 */
@Data
public class UserResp {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    private String username;

    /** 昵称 */
    private String nickname;

    /** 邮箱 */
    private String email;

    /** 手机号(脱敏) */
    private String phone;

    /** 状态 */
    private Integer status;

    /** 创建时间 */
    private String createTime;

    /** 更新时间 */
    private String updateTime;
}
