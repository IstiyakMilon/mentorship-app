package com.algorizin.mentorship.authorization.jwt.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String userId;
    private String password;
    private String role;
    private String status;
}
