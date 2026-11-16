package com.cr.user.service;

import com.cr.commons.result.PageResult;
import com.cr.user.dto.req.UserChangePwdReq;
import com.cr.user.dto.req.UserCreateReq;
import com.cr.user.dto.req.UserLoginReq;
import com.cr.user.dto.req.UserQueryReq;
import com.cr.user.dto.req.UserUpdateReq;
import com.cr.user.dto.resp.UserCreateResp;
import com.cr.user.dto.resp.UserLoginResp;
import com.cr.user.dto.resp.UserResp;
import com.cr.user.dto.resp.UserUpdateResp;

/**
 * 用户服务
 */
public interface UserService {

    /** 创建用户 */
    UserCreateResp createUser(UserCreateReq req);

    /** 按用户ID查询用户详情 */
    UserResp getUser(Long userId);

    /** 分页查询用户列表 */
    PageResult<UserResp> listUsers(UserQueryReq req);

    /** 更新用户信息 */
    UserUpdateResp updateUser(Long userId, UserUpdateReq req);

    /** 删除用户 */
    void deleteUser(Long userId);

    /** 用户登录 */
    UserLoginResp login(UserLoginReq req);

    /** 用户登出 */
    void logout(Long userId);

    /** 修改密码 */
    void changePassword(Long userId, UserChangePwdReq req);
}
