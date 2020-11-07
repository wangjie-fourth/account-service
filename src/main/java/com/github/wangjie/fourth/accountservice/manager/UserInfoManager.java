package com.github.wangjie.fourth.accountservice.manager;

import com.github.wangjie.fourth.accountservice.model.common.UserInfo;

public interface UserInfoManager {

    UserInfo getUserInfoByUserId(Long userId);
}
