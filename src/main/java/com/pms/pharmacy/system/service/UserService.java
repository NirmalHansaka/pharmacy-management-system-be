package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> getUsers(){
        return userRepository.findAllByIsActiveAndDeleted(true, false);
    }


    public User saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String deleteUser(int id){
        Optional<User> optionalUser = userRepository.findById(id);
       if (optionalUser.isPresent()){
           User user = optionalUser.get();
           user.setDeleted(true);
           userRepository.save(user);
           return "User removed";
       }

        return "User not exsist";
    }

    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setId(user.getId());
        existingUser.setUser(user.getUser());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setNicNo(user.getNicNo());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return  userRepository.save(existingUser);
    }
}
