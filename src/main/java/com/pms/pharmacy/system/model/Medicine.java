package com.pms.pharmacy.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Double unitPrice;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;
    private boolean deleted = false;
}


