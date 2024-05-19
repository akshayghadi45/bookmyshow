package com.example.bookmyshow;

import com.example.bookmyshow.controllers.UserController;
import com.example.bookmyshow.dtos.SignUpResponseStatus;
import com.example.bookmyshow.dtos.UserSignInRequestDto;
import com.example.bookmyshow.dtos.UserSignUpRequestDto;
import com.example.bookmyshow.dtos.UserSignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmyshowApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserSignUpRequestDto userSignUpRequestDto = new UserSignUpRequestDto();
		userSignUpRequestDto.setEmail("akshayghadi45@gmail.com");
		userSignUpRequestDto.setName("Akshay");
		userSignUpRequestDto.setPhoneNumber(77739213515L);
		userSignUpRequestDto.setPassword("password@123");

		UserSignUpResponseDto userSignUpResponseDto= userController.userSignUp(userSignUpRequestDto);
		if(userSignUpResponseDto.getSignUpResponseStatus().equals(SignUpResponseStatus.SUCCESS)){
			System.out.println("login is successful for: "+userSignUpResponseDto.getEmail());
		}


		UserSignInRequestDto userSignInRequestDto = new UserSignInRequestDto();
		userSignInRequestDto.setEmail("akshayghadi45@gmail.com");
		userSignInRequestDto.setPassword("password@123");
		if(userController.userSignIn(userSignInRequestDto)){
			System.out.println("Sign in successful");
		}


	}
}
