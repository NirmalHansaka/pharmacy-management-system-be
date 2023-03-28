package com.pms.pharmacy.system.controller;


import com.pms.pharmacy.system.model.Medicine;
import com.pms.pharmacy.system.model.Order;
import com.pms.pharmacy.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/getOrder")
    public List<Order> getOrders(){return orderService.getOrders(); }

    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }

    @PutMapping("/updateOrder")
    public Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }
    @DeleteMapping("/deleteOrder")
    public String deleteOrder(@RequestBody Integer id){
        return orderService.deleteOrder(id);
    }
}
