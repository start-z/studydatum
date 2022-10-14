package com.zhou.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private String username;
    private String name;
    private Integer age;
    private String email;
    private String avatar;
    private String phone;
}
