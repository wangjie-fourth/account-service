package com.github.wangjie.fourth.accountservice.controller;

import com.github.wangjie.fourth.accountservice.converter.c2s.UserInfoC2SConverter;
import com.github.wangjie.fourth.accountservice.exception.GlobalExceptionHandler;
import com.github.wangjie.fourth.accountservice.exception.ResourceNotFoundException;
import com.github.wangjie.fourth.accountservice.manager.UserInfoManager;
import com.github.wangjie.fourth.accountservice.model.common.UserInfo;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// Mockito的另一种实现方式
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    /**
     * mock MVC 下的特殊逻辑。比如：
     * Controller会有GlobalExceptionHandler
     */
    private MockMvc mockMvc;

    @Mock
    private UserInfoManager userInfoManager;
    @Mock
    private UserInfoC2SConverter userInfoC2SConverter;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup () {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                // 告诉测试mvc这个Controller是被增强的
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    public void teardown () {
        // note: 每个方法执行完成后，需要将其mock的方法剔除；以免影响下一个方法的mock
        reset(userInfoManager, userInfoC2SConverter);
    }

    @Test
    public void testGetUserInfoByUserId() throws Exception {
        // Arrange
        val userId = 100L;
        val username = "hardcore";
        val password = "hardcore";

        val userInfoInCommon = UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();

        val userInfo = com.github.wangjie.fourth.accountservice.model.service.UserInfo.builder()
                .id(userId)
                .username(username)
                .password(password)
                .build();
        doReturn(userInfoInCommon).when(userInfoManager).getUserInfoByUserId(anyLong());
        doReturn(userInfo).when(userInfoC2SConverter).convert(userInfoInCommon);

        // Act && Assert
        mockMvc.perform(get("/v1/users/100"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":100,\"username\":\"hardcore\",\"password\":\"hardcore\"}"));
        verify(userInfoManager).getUserInfoByUserId(anyLong());
        verify(userInfoC2SConverter).convert(userInfoInCommon);
    }

    @Test
    public void testGetUserInfoByUserIdWithInvalidParams() throws Exception {
        // Arrange
        val userId = -100L;
        doThrow(new ResourceNotFoundException("test")).when(userInfoManager).getUserInfoByUserId(userId);

        // Act && Assert
        mockMvc.perform(get("/v1/users/-100"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"there is legal parameter\",\"statusCode\":400}"));

    }


}
