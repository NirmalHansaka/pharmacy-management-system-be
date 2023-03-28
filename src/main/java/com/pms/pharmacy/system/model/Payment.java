package com.pms.pharmacy.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer payment_id;
    private Double amount;
    private Double discount;
    private Double total_amount;

    @ManyToOne
    @JoinColumn(name="payment_method_id", nullable=false)
    private PaymentMethod payment_method_id;
    private boolean deleted = false;



}
