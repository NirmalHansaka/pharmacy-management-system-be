package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Generic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenericRepository extends JpaRepository<Generic, Integer> {

    public List<Generic> findAllByDeleted(boolean deleted);
}
