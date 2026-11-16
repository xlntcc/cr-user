package com.cr.user.controller;

import com.cr.commons.result.PageResult;
import com.cr.commons.result.Result;
import com.cr.user.dto.req.UserChangePwdReq;
import com.cr.user.dto.req.UserCreateReq;
import com.cr.user.dto.req.UserLoginReq;
import com.cr.user.dto.req.UserQueryReq;
import com.cr.user.dto.req.UserUpdateReq;
import com.cr.user.dto.resp.UserCreateResp;
import com.cr.user.dto.resp.UserLoginResp;
import com.cr.user.dto.resp.UserResp;
import com.cr.user.dto.resp.UserUpdateResp;
import com.cr.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户中台接口
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** 创建用户 */
    @PostMapping
    public Result<UserCreateResp> createUser(@Valid @RequestBody UserCreateReq req) {
        return Result.success(userService.createUser(req));
    }

    /** 查询用户详情 */
    @GetMapping("/{userId}")
    public Result<UserResp> getUser(@PathVariable Long userId) {
        return Result.success(userService.getUser(userId));
    }

    /** 分页查询用户列表 */
    @PostMapping("/list")
    public Result<PageResult<UserResp>> listUsers(@RequestBody UserQueryReq req) {
        return Result.success(userService.listUsers(req));
    }

    /** 更新用户信息 */
    @PutMapping("/{userId}")
    public Result<UserUpdateResp> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateReq req) {
        return Result.success(userService.updateUser(userId, req));
    }

    /** 删除用户 */
    @DeleteMapping("/{userId}")
    public Result<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return Result.success();
    }

    /** 用户登录 */
    @PostMapping("/login")
    public Result<UserLoginResp> login(@Valid @RequestBody UserLoginReq req) {
        return Result.success(userService.login(req));
    }

    /** 用户登出 */
    @PostMapping("/logout")
    public Result<Void> logout(@PathVariable Long userId) {
        userService.logout(userId);
        return Result.success();
    }

    /** 修改密码 */
    @PutMapping("/{userId}/password")
    public Result<Void> changePassword(@PathVariable Long userId, @Valid @RequestBody UserChangePwdReq req) {
        userService.changePassword(userId, req);
        return Result.success();
    }
}
