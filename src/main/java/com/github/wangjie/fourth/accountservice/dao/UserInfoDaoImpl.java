package com.github.wangjie.fourth.accountservice.dao;

import com.github.wangjie.fourth.accountservice.dao.mapper.UserInfoMapper;
import com.github.wangjie.fourth.accountservice.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {


    private UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDaoImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoByUserId(id);
    }
}
