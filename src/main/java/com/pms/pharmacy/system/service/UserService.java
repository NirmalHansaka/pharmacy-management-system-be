package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.RoleRepository;
import com.pms.pharmacy.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public Role saveRole(Role role){
        return roleRepository.saveAndFlush(role);
    }

    public void saveUser(User user){
        userRepository.saveAndFlush(user);
    }

}
