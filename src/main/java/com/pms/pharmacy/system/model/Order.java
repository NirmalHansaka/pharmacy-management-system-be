package com.pms.pharmacy.system.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer_id;

    @ManyToOne
    @JoinColumn(name="payment_id", nullable=false)
    private Payment payment_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Date order_time;
    private boolean deleted = false;



}
