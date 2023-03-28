package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.Payment;
import com.pms.pharmacy.system.model.PaymentMethod;
import com.pms.pharmacy.system.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getPayments(){
        return paymentRepository.findAllByDeleted(false);
    }

    public Payment savePayment(Payment payment){
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment){
        Payment existingPayment = paymentRepository.findById(payment.getPayment_id()).orElse(null);
        existingPayment.setPayment_id(payment.getPayment_id());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setTotal_amount(payment.getTotal_amount());
        existingPayment.setDiscount(payment.getDiscount());
        existingPayment.setPayment_method_id(payment.getPayment_method_id());
        return paymentRepository.save(payment);
    }

    public String deletePayment(int id){
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()){
            Payment payment = optionalPayment.get();
            payment.setDeleted(true);
            paymentRepository.save(payment);
            return "Payment removed";
        }

        return "Payment not exsist";
    }
}
