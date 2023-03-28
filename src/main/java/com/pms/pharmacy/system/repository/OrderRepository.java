package com.pms.pharmacy.system.repository;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findAllByDeleted(boolean deleted);
}
