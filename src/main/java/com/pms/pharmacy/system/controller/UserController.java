package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.User;

import com.pms.pharmacy.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
