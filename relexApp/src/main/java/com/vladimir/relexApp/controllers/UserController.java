package com.vladimir.relexApp.controllers;


import com.vladimir.relexApp.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/staff")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Удаление сотрудника по id",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        userService.dismissStaff(id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Вывод списка всех сотрудников",
            description = "Уровень доступа OWNER"
    )
    @PreAuthorize("hasRole('OWNER')")
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAllStaff());
    }

    @Operation(
            summary = "Вывод информации о сотруднике",
            description = "Уровень доступа Authenticated"
    )
    @PreAuthorize("isAuthenticated")
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(userService.getInfoUser(token));
    }
}
