package com.vladimir.relexApp.services;

import com.vladimir.relexApp.Mappers.ProductMapper;
import com.vladimir.relexApp.dtos.RegistrationUserDto;
import com.vladimir.relexApp.dtos.UserResponseDto;
import com.vladimir.relexApp.entity.User;
import com.vladimir.relexApp.repositories.ChangeListRepository;
import com.vladimir.relexApp.repositories.UserRepository;
import com.vladimir.relexApp.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final RoleService roleService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtils jwtTokenUtils;

    private final ChangeListRepository changeListRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService, JwtTokenUtils jwtTokenUtils, ChangeListRepository changeListRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.changeListRepository = changeListRepository;
    }

    public Optional<User> findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long getUserId(String email) {
        try {
            return findByUserEmail(email).get().getId();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
    @Transactional
    public User createNewUser(RegistrationUserDto registrationUserDto) {

        User user = ProductMapper.INSTANCE.registrationUserDtoToUser(registrationUserDto);
        user.setRole(roleService.getUserRole());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public void dismissStaff(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователь с таким id не найден");
        }
        changeListRepository.deleteByUserId(id);
        userRepository.deleteById(id);
    }


    public List<UserResponseDto> getAllStaff() {
        var users = userRepository.findAll(Sort.by("id"));

        return users.stream()
                .map(ProductMapper.INSTANCE::userToUserResponseDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto getInfoUser(String header) {
        String token = header.substring(7);
        User user = userRepository.findByEmail(jwtTokenUtils.getEmail(token))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь не найден"));
        return ProductMapper.INSTANCE.userToUserResponseDto(user);
    }
}
