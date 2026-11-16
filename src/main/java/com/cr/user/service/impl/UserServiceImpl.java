package com.cr.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cr.commons.enums.UserStatusEnum;
import com.cr.commons.exception.BizException;
import com.cr.commons.result.PageResult;
import com.cr.commons.result.ResultCode;
import com.cr.commons.util.DateUtils;
import com.cr.user.dto.req.UserChangePwdReq;
import com.cr.user.dto.req.UserCreateReq;
import com.cr.user.dto.req.UserLoginReq;
import com.cr.user.dto.req.UserQueryReq;
import com.cr.user.dto.req.UserUpdateReq;
import com.cr.user.dto.resp.UserCreateResp;
import com.cr.user.dto.resp.UserLoginResp;
import com.cr.user.dto.resp.UserResp;
import com.cr.user.dto.resp.UserUpdateResp;
import com.cr.user.entity.User;
import com.cr.user.mapper.UserMapper;
import com.cr.user.service.UserService;
import com.cr.user.support.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final UserConverter converter;

    /** 创建用户 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserCreateResp createUser(UserCreateReq req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        user.setNickname(req.getNickname());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setStatus(UserStatusEnum.ENABLE.getCode());
        userMapper.insert(user);

        UserCreateResp resp = new UserCreateResp();
        resp.setUserId(String.valueOf(user.getUserId()));
        resp.setUsername(user.getUsername());
        resp.setCreatedAt(DateUtils.format(user.getCreateTime()));
        return resp;
    }

    /** 按用户ID查询用户详情 */
    @Override
    public UserResp getUser(Long userId) {
        return converter.toResp(getByIdOrThrow(userId));
    }

    /** 分页查询用户列表 */
    @Override
    public PageResult<UserResp> listUsers(UserQueryReq req) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(req.getKeyword())) {
            String keyword = req.getKeyword();
            wrapper.and(w -> w.like(User::getUsername, keyword)
                    .or().like(User::getNickname, keyword)
                    .or().like(User::getPhone, keyword));
        }
        if (req.getStatus() != null) {
            wrapper.eq(User::getStatus, req.getStatus());
        }
        wrapper.orderByDesc(User::getCreateTime);

        Page<User> page = userMapper.selectPage(new Page<>(req.getPageNum(), req.getPageSize()), wrapper);
        List<UserResp> list = page.getRecords().stream()
                .map(converter::toResp)
                .collect(Collectors.toList());
        return PageResult.of(list, page.getTotal(), req.getPageNum(), req.getPageSize());
    }

    /** 更新用户信息 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserUpdateResp updateUser(Long userId, UserUpdateReq req) {
        User user = getByIdOrThrow(userId);
        user.setNickname(req.getNickname());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setStatus(req.getStatus());
        userMapper.updateById(user);

        UserUpdateResp resp = new UserUpdateResp();
        resp.setUserId(String.valueOf(user.getUserId()));
        resp.setUsername(user.getUsername());
        resp.setUpdatedAt(DateUtils.format(user.getUpdateTime()));
        return resp;
    }

    /** 删除用户 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long userId) {
        User user = getByIdOrThrow(userId);
        userMapper.deleteById(user.getUserId());
    }

    /** 用户登录 */
    @Override
    public UserLoginResp login(UserLoginReq req) {
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>().eq(User::getUsername, req.getUsername()));
        if (user == null || !BCrypt.checkpw(req.getPassword(), user.getPassword())) {
            throw new BizException(ResultCode.UNAUTHORIZED, "用户名或密码错误");
        }
        if (UserStatusEnum.DISABLE.getCode().equals(user.getStatus())) {
            throw new BizException(ResultCode.UNAUTHORIZED, "账号已被禁用");
        }
        UserLoginResp resp = new UserLoginResp();
        resp.setUserId(String.valueOf(user.getUserId()));
        resp.setUsername(user.getUsername());
        resp.setNickname(user.getNickname());
        resp.setLoginTime(DateUtils.format(LocalDateTime.now()));
        return resp;
    }

    /** 用户登出 */
    @Override
    public void logout(Long userId) {
        getByIdOrThrow(userId);
    }

    /** 修改密码 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(Long userId, UserChangePwdReq req) {
        User user = getByIdOrThrow(userId);
        if (!BCrypt.checkpw(req.getOldPassword(), user.getPassword())) {
            throw new BizException(ResultCode.BAD_REQUEST, "原密码不正确");
        }
        user.setPassword(BCrypt.hashpw(req.getNewPassword(), BCrypt.gensalt()));
        userMapper.updateById(user);
    }

    /** 按ID查询用户，不存在则抛出业务异常 */
    private User getByIdOrThrow(Long userId) {
        if (userId == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BizException(ResultCode.NOT_FOUND, "用户不存在");
        }
        return user;
    }
}
