package com.github.wangjie.fourth.accountservice.controller;

import com.github.wangjie.fourth.accountservice.converter.c2s.UserInfoC2SConverter;
import com.github.wangjie.fourth.accountservice.exception.InvalidParameterException;
import com.github.wangjie.fourth.accountservice.manager.UserInfoManager;
import com.github.wangjie.fourth.accountservice.model.service.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("v1/users")
public class UserController {

    private UserInfoManager userInfoManager;
    private UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("id") Long userId) {
        if (userId == null || userId <= 0L) {
            throw new InvalidParameterException("there is legal parameter");
        }

        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(userInfoC2SConverter.convert(userInfo));
    }
}
