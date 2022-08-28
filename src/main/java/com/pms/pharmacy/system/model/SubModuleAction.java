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
public class SubModuleAction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String path;

    private String method;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sub_module_id", nullable=false)
    private SubModule subModule;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="action_id", nullable=false)
    private Action action;

    @OneToMany(mappedBy="subModuleAction")
    @JsonIgnore
    private List<RoleSubModuleAction> roleSubModuleActions;
}
