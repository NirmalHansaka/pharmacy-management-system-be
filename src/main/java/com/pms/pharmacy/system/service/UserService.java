package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;
    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
