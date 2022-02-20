package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.UserExistsInRepositoryException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final DbServiceUser dbServiceUser;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserExistsInRepositoryException, CartNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(dbServiceUser.createUser(userDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> blockUser (@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(dbServiceUser.blockUser(id)));
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> generateKey(@PathVariable String username, @RequestParam String password) throws UserNotFoundException {
        return ResponseEntity.ok(dbServiceUser.generateKey(username, password));
    }
}