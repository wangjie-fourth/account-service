package com.github.wangjie.fourth.accountservice.converter.p2c;

import com.github.wangjie.fourth.accountservice.model.persistence.UserInfo;
import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;


@Component
@EqualsAndHashCode
public class UserInfoP2CConverter extends Converter<UserInfo, com.github.wangjie.fourth.accountservice.model.common.UserInfo> {
    @Override
    protected com.github.wangjie.fourth.accountservice.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.github.wangjie.fourth.accountservice.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.github.wangjie.fourth.accountservice.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
