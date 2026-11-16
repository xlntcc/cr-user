package com.cr.user.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * 分页查询用户请求
 */
@Data
public class UserQueryReq {

    /** 模糊查询关键词 */
    private String keyword;

    /** 状态 0-禁用 1-启用 */
    private Integer status;

    /** 页码，从1开始 */
    @Min(value = 1, message = "页码最小为1")
    private Integer pageNum = 1;

    /** 每页大小 */
    @Min(value = 1, message = "每页大小最小为1")
    @Max(value = 100, message = "每页大小最大为100")
    private Integer pageSize = 20;
}
