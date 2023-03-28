package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    public List<PaymentMethod> findAllByDeleted(boolean deleted);
}
