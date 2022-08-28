package com.pms.pharmacy.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubModule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToOne()
    @JoinColumn(name="module_id", nullable=false)
    private Module module;

    @OneToMany(mappedBy="subModule")
    @JsonIgnore
    private List<SubModuleAction> subModuleActions;
}
