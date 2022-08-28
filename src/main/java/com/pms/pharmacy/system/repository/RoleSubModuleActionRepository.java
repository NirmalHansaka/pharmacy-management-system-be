package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.RoleSubModuleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleSubModuleActionRepository extends JpaRepository<RoleSubModuleAction, Integer> {

    @Query("SELECT roleSubModuleAction FROM RoleSubModuleAction roleSubModuleAction WHERE " +
                    "roleSubModuleAction.role.name=?1 " +
                    "AND roleSubModuleAction.subModuleAction.path=?2 " +
                    "AND roleSubModuleAction.subModuleAction.method=?3 ")
    List<RoleSubModuleAction> findByRoleName(String roleName, String path, String method);
}
