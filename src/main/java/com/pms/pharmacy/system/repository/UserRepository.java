package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public List<User> findAllByIsActiveAndDeleted(boolean isActive, boolean deleted);


}
