package com.github.wangjie.fourth.accountservice.manager;


import com.github.wangjie.fourth.accountservice.converter.p2c.UserInfoP2CConverter;
import com.github.wangjie.fourth.accountservice.dao.UserInfoDao;
import com.github.wangjie.fourth.accountservice.exception.ResourceNotFoundException;
import com.github.wangjie.fourth.accountservice.model.common.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserInfoManagerImpl implements UserInfoManager {
    private UserInfoDao userInfoDao;
    private UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoById(userId))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("user %s was not found", userId)));

        return userInfoP2CConverter.convert(userInfo);
    }
}
