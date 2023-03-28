package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    public List<Payment> findAllByDeleted(boolean deleted);
}
