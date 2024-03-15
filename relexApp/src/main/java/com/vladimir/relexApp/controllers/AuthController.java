package com.vladimir.relexApp.controllers;

import com.vladimir.relexApp.dtos.JwtRequestDto;
import com.vladimir.relexApp.dtos.RegistrationUserDto;
import com.vladimir.relexApp.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контроллер для регистрации и авторизации")
@RestController
public class AuthController {


    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
            summary = "Регистрирует нового пользователя",
            description = "Получает DTO пользователя и передает объект в сервис"
    )
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto){
        return ResponseEntity.ok(authService.createNewUser(registrationUserDto)) ;
    }

    @Operation(
            summary = "Аутентифицирует пользователя",
            description = "Получает DTO от пользователя и возвращает токен"
    )
    @PostMapping("/auth")
    public ResponseEntity<?> createUserToken(@RequestBody JwtRequestDto jwtRequestDto){
        return ResponseEntity.ok(authService.createUserToken(jwtRequestDto));
    }

}
