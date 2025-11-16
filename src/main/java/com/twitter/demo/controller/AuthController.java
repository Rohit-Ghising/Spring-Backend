package com.twitter.demo.controller;

import com.twitter.demo.config.JwtProvider;
import com.twitter.demo.exception.UserException;
import com.twitter.demo.model.User;
import com.twitter.demo.model.Varification;
import com.twitter.demo.repository.UserRepository;
import com.twitter.demo.response.AuthResponse;
import com.twitter.demo.service.CustomUserDetailsServiceImplementation;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder ;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomUserDetailsServiceImplementation customUserDetails;
    @PostMapping("/signup")

    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        String birthDate = user.getBirthDate();

        User isEmailExist = userRepository.findByEmail(email);
        if(isEmailExist != null){
            throw new UserException("Email already exists");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);

        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setBirthDate((birthDate));
        createdUser.setVerification(new Varification());

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,true) ;
        return  new  ResponseEntity<AuthResponse>(res, HttpStatus.CREATED);






    }
    @PostMapping("/signin")

    public ResponseEntity<AuthResponse> signin(@RequestBody User user)

    {
        System.out.println("user"+ user);
        String username = user.getEmail();
        String password = user.getPassword();
        Authentication authentication = authenticate(username,password);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,true) ;
        return  new  ResponseEntity<AuthResponse>(res, HttpStatus.ACCEPTED);



    }
@GetMapping("/profile")
    private Authentication authenticate(String username, String password) {
        UserDetails userDetails =customUserDetails.loadUserByUsername(username);
        if(userDetails == null){
            throw  new BadCredentialsException("Invalid user name");

        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw  new BadCredentialsException("username and passord dobt match");

        }
        return  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }


}
