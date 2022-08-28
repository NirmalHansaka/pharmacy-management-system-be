package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.*;
import com.pms.pharmacy.system.model.Module;
import com.pms.pharmacy.system.repository.*;
import com.pms.pharmacy.system.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleSubModuleActionRepository roleSubModuleActionRepository;
    private final ActionRepository actionRepository;
    private final ModuleRepository moduleRepository;
    private final SubModuleRepository subModuleRepository;
    private final SubModuleActionRepository subModuleActionRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("User not found");

        // set roles
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority((user.getRole().getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }

    public void initUserData(){

        long userCount = userRepository.count();

        if(userCount == 0){

            // save default admin and roles
            Role admin = saveRole(new Role(null, "ADMIN", null, null));
            saveRole(new Role(null, "CASHIER", null, null));

            saveUser(new User(null, "defaultadmin", "123", "default",
                    "admin", "961111111v", "0711231231", admin));

            // Save Admin default privileges with modules
            Module userManagement = new Module(null, "User Management", null);
            SubModule user = new SubModule(null, "User", userManagement, null);

            Action view = new Action(null, Constants.VIEW, null);
            Action add = new Action(null, Constants.ADD, null);
            Action update = new Action(null, Constants.UPDATE, null);
            Action delete = new Action(null, Constants.DELETE, null);

            SubModuleAction userView = new SubModuleAction(null, "/api/users", HttpMethod.GET.name(), user, view, null);
            SubModuleAction userAdd = new SubModuleAction(null, "/api/users", HttpMethod.POST.name(), user, add, null);
            SubModuleAction userUpdate = new SubModuleAction(null, "/api/users", HttpMethod.PUT.name(), user, update, null);
            SubModuleAction userDelete = new SubModuleAction(null, "/api/users", HttpMethod.DELETE.name(), user, delete, null);

            RoleSubModuleAction adminUserView = new RoleSubModuleAction(null, userView, admin);
            RoleSubModuleAction adminUserAdd = new RoleSubModuleAction(null, userAdd, admin);
            RoleSubModuleAction adminUserUpdate = new RoleSubModuleAction(null, userUpdate, admin);
            RoleSubModuleAction adminUserDelete = new RoleSubModuleAction(null, userDelete, admin);

            List<RoleSubModuleAction> roleSubModuleActions = List.of(adminUserView, adminUserAdd, adminUserUpdate, adminUserDelete);

            moduleRepository.save(userManagement);
            subModuleRepository.save(user);

            for (RoleSubModuleAction roleSubModuleAction: roleSubModuleActions) {
                actionRepository.save(roleSubModuleAction.getSubModuleAction().getAction());
                subModuleActionRepository.save(roleSubModuleAction.getSubModuleAction());
                roleSubModuleActionRepository.save(roleSubModuleAction);
            }
        }

    }
}
