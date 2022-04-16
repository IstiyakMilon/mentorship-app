package com.algorizin.mentorship.util;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionHandlerUtil extends Exception {
    public HttpStatus code;
    public String message;
}
