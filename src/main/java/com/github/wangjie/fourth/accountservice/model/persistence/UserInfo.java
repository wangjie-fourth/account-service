package com.github.wangjie.fourth.accountservice.model.persistence;

import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    private Long id;

    private String username;

    private String password;

    private LocalDate createTime;

    private LocalDate updateTime;

}
