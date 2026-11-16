package com.cr.user.support;

import com.cr.commons.util.DateUtils;
import com.cr.user.dto.resp.UserResp;
import com.cr.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户对象转换器
 */
@Component
@RequiredArgsConstructor
public class UserConverter {

    /** 实体转响应对象，手机号脱敏 */
    public UserResp toResp(User user) {
        if (user == null) {
            return null;
        }
        UserResp resp = new UserResp();
        resp.setUserId(String.valueOf(user.getUserId()));
        resp.setUsername(user.getUsername());
        resp.setNickname(user.getNickname());
        resp.setEmail(user.getEmail());
        resp.setPhone(maskPhone(user.getPhone()));
        resp.setStatus(user.getStatus());
        resp.setCreateTime(DateUtils.format(user.getCreateTime()));
        resp.setUpdateTime(DateUtils.format(user.getUpdateTime()));
        return resp;
    }

    /** 手机号脱敏，保留前3后4位 */
    public String maskPhone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return null;
        }
        if (phone.length() < 7) {
            return "****";
        }
        return phone.substring(0, 3) + "****" + phone.substring(phone.length() - 4);
    }
}
