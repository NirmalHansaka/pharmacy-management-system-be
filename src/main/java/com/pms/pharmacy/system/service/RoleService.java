package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAllByDeleted(false);
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }

    public Role updateRole(Role role){
        Role existingRole = roleRepository.findById(role.getRole_id()).orElse(null);
        existingRole.setRole_id(role.getRole_id());
        existingRole.setRole_name(role.getRole_name());
        return roleRepository.save(existingRole);
    }

    public String deleteRole(int id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role role = optionalRole.get();
            role.setDeleted(true);
            roleRepository.save(role);
            return "Role removed";
        }

        return "Role not exsist";
    }
}
