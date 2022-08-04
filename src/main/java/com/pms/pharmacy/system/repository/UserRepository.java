package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
