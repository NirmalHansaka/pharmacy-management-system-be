package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
