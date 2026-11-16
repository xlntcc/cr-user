package com.cr.user.api;

import com.cr.commons.result.PageResult;
import com.cr.commons.result.Result;
import com.cr.user.dto.req.UserCreateReq;
import com.cr.user.dto.req.UserQueryReq;
import com.cr.user.dto.req.UserUpdateReq;
import com.cr.user.dto.resp.UserCreateResp;
import com.cr.user.dto.resp.UserResp;
import com.cr.user.dto.resp.UserUpdateResp;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户中台对外Feign接口，供其他服务调用
 */
@FeignClient(name = "cr-user", contextId = "userApi")
public interface UserApi {

    /** 创建用户 */
    @PostMapping("/api/v1/users")
    Result<UserCreateResp> createUser(@Valid @RequestBody UserCreateReq req);

    /** 查询用户详情 */
    @GetMapping("/api/v1/users/{userId}")
    Result<UserResp> getUser(@PathVariable Long userId);

    /** 分页查询用户列表 */
    @PostMapping("/api/v1/users/list")
    Result<PageResult<UserResp>> listUsers(@RequestBody UserQueryReq req);

    /** 更新用户信息 */
    @PutMapping("/api/v1/users/{userId}")
    Result<UserUpdateResp> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateReq req);

    /** 删除用户 */
    @DeleteMapping("/api/v1/users/{userId}")
    Result<Void> deleteUser(@PathVariable Long userId);
}
