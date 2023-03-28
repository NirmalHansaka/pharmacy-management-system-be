package com.pms.pharmacy.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer generic_id;
    private String generic_name;
    private boolean deleted = false;
}
