package com.github.wangjie.fourth.accountservice.model.service;

import lombok.*;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String password;
}
