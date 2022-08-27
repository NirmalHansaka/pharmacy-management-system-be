package com.pms.pharmacy.system;

import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PharmacySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmacySystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            Role admin = userService.saveRole(new Role(null, "ROLE_ADMIN", null));
            userService.saveRole(new Role(null, "ROLE_CASHIER", null));

            userService.saveUser(new User(null, "defaultadmin", "123", "default", "admin", "961111111v", "0711231231", admin));

        };
    }

}
