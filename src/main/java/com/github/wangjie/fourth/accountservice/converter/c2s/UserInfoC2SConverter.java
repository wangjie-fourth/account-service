package com.github.wangjie.fourth.accountservice.converter.c2s;

import com.github.wangjie.fourth.accountservice.model.common.UserInfo;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoC2SConverter extends Converter<UserInfo, com.github.wangjie.fourth.accountservice.model.service.UserInfo> {

    @Override
    protected com.github.wangjie.fourth.accountservice.model.service.UserInfo doForward(UserInfo userInfo) {
        return com.github.wangjie.fourth.accountservice.model.service.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.github.wangjie.fourth.accountservice.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .build();
    }
}
