package com.pms.pharmacy.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String user;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Integer nicNo;
    private Integer phoneNumber;
    @JsonIgnore
    private boolean isActive = true;
    @JsonIgnore
    private boolean deleted = false;
}
