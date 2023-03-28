package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    public List<Company> findAllByDeleted(boolean deleted);

}
