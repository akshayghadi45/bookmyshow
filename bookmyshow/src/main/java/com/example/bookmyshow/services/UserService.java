package com.example.bookmyshow.services;

import com.example.bookmyshow.Models.User;
import com.example.bookmyshow.exceptions.InvalidPasswordException;
import com.example.bookmyshow.exceptions.UserAlreadyPresentException;
import com.example.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bookmyshow.exceptions.UserNotPresentException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User userSignUp(String email, String password, Long phoneNumber,String name) throws UserAlreadyPresentException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            throw new UserAlreadyPresentException("User already present. please sign in");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(encoder.encode(password));
        user.setPhoneNumber(phoneNumber);

        return  userRepository.save(user);
    }

    public boolean userSignIn(String email, String password) throws UserNotPresentException, InvalidPasswordException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //String encryptedPassword = encoder.encode(password);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotPresentException("Please sign up before signing in");
        }
        User user = optionalUser.get();
        if(!encoder.matches(password,user.getPassword())){
            throw new InvalidPasswordException("Password is invalid");
//        String userPasssword = user.getPassword();
//        if(encryptedPassword.equals(userPasssword)){
//            return true;
        }
        return true;
    }
}
