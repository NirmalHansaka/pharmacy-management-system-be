package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/getRole")
    public List<Role> getRoles(){return roleService.getRoles(); }

    @PostMapping("/addRole")
    public Role addRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @PutMapping("/updateRole")
    public Role updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @DeleteMapping("/deleteRole/{id}")

    public String deleteRole(@PathVariable Integer id) {
        return roleService.deleteRole(id);
    }
}
