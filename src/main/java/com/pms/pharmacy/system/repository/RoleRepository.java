package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
