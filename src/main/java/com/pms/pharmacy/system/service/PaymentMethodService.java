package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.Medicine;
import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodService {
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethod> getPaymentMethods(){
        return paymentMethodRepository.findAllByDeleted(false);
    }

    public PaymentMethod savePaymentMethod(PaymentMethod paymentMethod){
        return paymentMethodRepository.save(paymentMethod);
    }

    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod){
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(paymentMethod.getPayment_method_id()).orElse(null);
        existingPaymentMethod.setPayment_method_id(paymentMethod.getPayment_method_id());
        existingPaymentMethod.setPayment_method(paymentMethod.getPayment_method());
        return paymentMethodRepository.save(existingPaymentMethod);
    }
    public String deletePaymentMethod(int id){
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(id);
        if (optionalPaymentMethod.isPresent()){
            PaymentMethod paymentMethod = optionalPaymentMethod.get();
            paymentMethod.setDeleted(true);
            paymentMethodRepository.save(paymentMethod);
            return "Payment Method removed";
        }

        return "Payment Method not exsist";
    }

}
