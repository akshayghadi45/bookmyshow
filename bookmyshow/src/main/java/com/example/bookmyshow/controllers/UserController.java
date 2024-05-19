package com.example.bookmyshow.controllers;

import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.dtos.SignUpResponseStatus;
import com.example.bookmyshow.dtos.UserSignUpRequestDto;
import com.example.bookmyshow.dtos.UserSignUpResponseDto;
import com.example.bookmyshow.dtos.UserSignInRequestDto;
import com.example.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    public UserSignUpResponseDto userSignUp(UserSignUpRequestDto requestDto){
        try{
            User user = userService.userSignUp(requestDto.getEmail(),requestDto.getPassword(),requestDto.getPhoneNumber(), requestDto.getName());
            UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
            userSignUpResponseDto.setEmail(user.getEmail());
            userSignUpResponseDto.setSignUpResponseStatus(SignUpResponseStatus.SUCCESS);
            return userSignUpResponseDto;
        }
        catch (Exception e) {
            System.out.println("Error occurred while signing up" + e.getMessage());
            UserSignUpResponseDto userSignUpResponseDto = new UserSignUpResponseDto();
            userSignUpResponseDto.setEmail(null);
            userSignUpResponseDto.setSignUpResponseStatus(SignUpResponseStatus.FAILED);
            return userSignUpResponseDto;
        }
    }

    public Boolean userSignIn(UserSignInRequestDto requestDto){
        try{
            boolean isSignOn = userService.userSignIn(requestDto.getEmail(),requestDto.getPassword());
            return isSignOn;
        }
        catch(Exception e){
            System.out.println("Error signing in"+e.getMessage());
            return false;
        }

    }
}
