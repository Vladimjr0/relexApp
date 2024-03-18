package com.vladimir.relexApp.service;

import com.vladimir.relexApp.dto.JwtRequestDto;
import com.vladimir.relexApp.dto.JwtResponseDto;
import com.vladimir.relexApp.dto.RegistrationUserDto;
import com.vladimir.relexApp.dto.UserResponseDto;
import com.vladimir.relexApp.entity.User;
import com.vladimir.relexApp.repository.UserRepository;
import com.vladimir.relexApp.util.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtTokenUtils jwtTokenUtils;

    protected final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, UserService userService, JwtTokenUtils jwtTokenUtils, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.userRepository = userRepository;
    }

    public UserResponseDto createNewUser(RegistrationUserDto registrationUserDto){
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пароли не совпадают");
        }
        if(userRepository.existsByEmail(registrationUserDto.getEmail())){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "Сотрудник с такой почтой уже существует");
        }
        User user = userService.createNewUser(registrationUserDto);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getAvrRating());
    }


    public JwtResponseDto createUserToken(JwtRequestDto jwtRequestDto){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDto.getEmail(), jwtRequestDto.getPassword()));
        }catch (BadCredentialsException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Отказано в доступе");
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.getEmail());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);

    }
}
