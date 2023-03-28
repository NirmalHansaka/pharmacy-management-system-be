package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.Payment;
import com.pms.pharmacy.system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/getPayments")
    public List<Payment> getPayments(){return paymentService.getPayments(); }

    @PostMapping("/addPayment")
    public Payment addPayment(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }

    @PutMapping("/updatePayment")
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.updatePayment(payment);
    }

    @DeleteMapping("/deletePayment")
    public String deletePayment(@RequestBody Integer id){
        return paymentService.deletePayment(id);
    }
}
