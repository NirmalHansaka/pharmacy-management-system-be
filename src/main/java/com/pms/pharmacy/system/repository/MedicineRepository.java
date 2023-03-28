package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    public List<Medicine> findAllByDeleted(boolean deleted);
}
