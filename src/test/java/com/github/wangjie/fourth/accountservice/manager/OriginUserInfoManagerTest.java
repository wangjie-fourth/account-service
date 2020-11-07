package com.github.wangjie.fourth.accountservice.manager;

import com.github.wangjie.fourth.accountservice.converter.p2c.UserInfoP2CConverter;
import com.github.wangjie.fourth.accountservice.dao.UserInfoDaoImpl;
import com.github.wangjie.fourth.accountservice.dao.mapper.UserInfoMapper;
import com.github.wangjie.fourth.accountservice.exception.ResourceNotFoundException;
import com.github.wangjie.fourth.accountservice.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 不引入Mock框架的原始测试写法
 * 缺点：
 * 为了写Mock依赖，写很多伪实现代码
 */
public class OriginUserInfoManagerTest {

    private UserInfoManager userInfoManager;

    @BeforeEach
    public void setUp() {
        UserInfoMapper userInfoMapper = new UserInfoMapperTestImpl();
        val userInfoP2CConverter = new UserInfoP2CConverter();
        val userInfoDAO = new UserInfoDaoImpl(userInfoMapper);

        userInfoManager = new UserInfoManagerImpl(userInfoDAO, userInfoP2CConverter);
    }

    @Test
    public void testGetUserInfoByUserId() {
        // Arrange
        val userId = 1L;

        // Act
        val result = userInfoManager.getUserInfoByUserId(userId);

        // Assert
        Assertions.assertEquals(userId, result.getId());
        Assertions.assertEquals("hardcore", result.getUsername());
    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidUserId() {
        // Arrange
        val userId = -1L;

        // Act && Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userInfoManager.getUserInfoByUserId(userId));

    }

    /**
     * for Test
     */
    private static class UserInfoMapperTestImpl implements UserInfoMapper {

        @Override
        public UserInfo getUserInfoByUserId(Long id) {
            if (id <= 0L) {
                throw new ResourceNotFoundException("resource Not found");
            }
            return UserInfo.builder()
                    .id(id)
                    .username("hardcore")
                    .password("hardcore")
                    .build();
        }

    }
}
