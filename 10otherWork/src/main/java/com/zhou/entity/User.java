package com.zhou.entity;

import com.zhou.annotation.TestValid;
import lombok.*;

import javax.validation.Valid;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    @Valid
    @TestValid(message = "默认消息不能为空")
    private String username;
    private String name;
    private Integer age;
    private String email;
    private String avatar;
    private String phone;
}
