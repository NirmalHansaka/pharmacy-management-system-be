package com.pms.pharmacy.system.service;


import com.pms.pharmacy.system.model.Order;
import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrders(){
        return orderRepository.findAllByDeleted(false);
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order){
        Order existingOrder = orderRepository.findById(order.getOrder_id()).orElse(null);
        existingOrder.setOrder_id(order.getOrder_id());
        existingOrder.setCustomer_id(order.getCustomer_id());
        existingOrder.setOrder_date(order.getOrder_date());
        existingOrder.setOrder_time(order.getOrder_time());
        existingOrder.setPayment_id(order.getPayment_id());
        return orderRepository.save(existingOrder);
}

    public String deleteOrder(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setDeleted(true);
            orderRepository.save(order);
            return "Order removed";
        }

        return "Order not exsist";
    }
}
