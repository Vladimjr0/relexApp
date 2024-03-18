package com.vladimir.relexApp.controller;

import com.vladimir.relexApp.dto.JwtRequestDto;
import com.vladimir.relexApp.dto.RegistrationUserDto;
import com.vladimir.relexApp.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
            description = "Получает DTO пользователя и передает объект в сервис, где регистрирует его, добавляя в БД"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован",
                    content = @Content(schema = @Schema(implementation = RegistrationUserDto.class)))
    }
    )
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return ResponseEntity.ok(authService.createNewUser(registrationUserDto));
    }

    @Operation(
            summary = "Аутентифицирует пользователя",
            description = "Получает DTO от пользователя и возвращает токен"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аутентификации пользователя прошла успешно",
                    content = @Content(schema = @Schema(implementation = JwtRequestDto.class)))
    })
    @PostMapping("/auth")
    public ResponseEntity<?> createUserToken(@RequestBody JwtRequestDto jwtRequestDto) {
        return ResponseEntity.ok(authService.createUserToken(jwtRequestDto));
    }

}
