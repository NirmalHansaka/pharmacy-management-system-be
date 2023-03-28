package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.Order;
import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/getPaymentMethods")
    public List<PaymentMethod> getPaymentMethods(){return paymentMethodService.getPaymentMethods(); }

    @PostMapping("/addPaymentMethod")
    public PaymentMethod addPaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.savePaymentMethod(paymentMethod);
    }

    @PutMapping("/updatePaymentMethod")
    public PaymentMethod updatePaymentMethod(@RequestBody PaymentMethod paymentMethod){
        return paymentMethodService.updatePaymentMethod(paymentMethod);
    }

    @DeleteMapping("/deletePaymentMethod")
    public String deletePaymentMethod(@RequestParam Integer id){
        return paymentMethodService.deletePaymentMethod(id);
    }
}
