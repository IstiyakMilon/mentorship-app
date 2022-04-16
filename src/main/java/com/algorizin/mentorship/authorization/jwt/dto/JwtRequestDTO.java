package com.algorizin.mentorship.authorization.jwt.dto;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequestDTO implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String userId;
    private String password;
}
