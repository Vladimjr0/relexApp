package com.vladimir.relexApp.service;

import com.vladimir.relexApp.entity.Role;
import com.vladimir.relexApp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService{

    private final RoleRepository roleRepository;

    public Role getUserRole(){
        return roleRepository.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Такая роль не найдена"));
    }



}
