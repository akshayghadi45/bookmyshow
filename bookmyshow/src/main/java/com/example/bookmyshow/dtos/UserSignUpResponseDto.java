package com.example.bookmyshow.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpResponseDto {
    private String email;
    private SignUpResponseStatus signUpResponseStatus;
}
