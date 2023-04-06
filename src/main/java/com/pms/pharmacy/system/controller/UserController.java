package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.model.User;

import com.pms.pharmacy.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserService userService;



    @GetMapping("/GetUsers")
    public List<User> getUsers(){

        return userService.getUsers();

    }

    @PostMapping("/addUser")
    public User addRole(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody Integer id){
         return userService.deleteUser(id);
    }

}
