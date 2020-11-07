package com.github.wangjie.fourth.accountservice.manager;

import com.github.wangjie.fourth.accountservice.converter.p2c.UserInfoP2CConverter;
import com.github.wangjie.fourth.accountservice.dao.UserInfoDao;
import com.github.wangjie.fourth.accountservice.model.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class MockitoUserInfoManagerTest {

    private UserInfoManager userInfoManager;

    @Mock
    private UserInfoDao userInfoDAO;


    @BeforeEach
    public void setup () {
        MockitoAnnotations.initMocks(this);
        userInfoManager = new UserInfoManagerImpl(userInfoDAO, new UserInfoP2CConverter());
    }

    @Test
    public void testGetUserInfoByUserId () {
        // Arrange
        val userId = 1L;
        val userInfo = UserInfo.builder()
                .id(1L)
                .username("hardcore")
                .password("hardcore")
                .build();

        doReturn(userInfo).when(userInfoDAO).getUserInfoById(userId);

        // Act
        val result = userInfoManager.getUserInfoByUserId(userId);

        // Assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", "hardcore")
                .hasFieldOrPropertyWithValue("password", "hardcore");
        verify(userInfoDAO).getUserInfoById(userId);
    }
}
