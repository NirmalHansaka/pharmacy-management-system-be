package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.Generic;
import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenericController {

    @Autowired
    GenericService genericService;

    @GetMapping("/getGenerics")
    public List<Generic> getGenerics(){return genericService.getGeneric(); }

    @PostMapping("/addGeneric")
    public Generic addGeneric(@RequestBody Generic generic){
        return genericService.saveGeneric(generic);
    }

    @PutMapping("/updateGeneric")
    public Generic updateGeneric(@RequestBody Generic generic){
        return genericService.updateGeneric(generic);
    }

    @DeleteMapping("/deleteGeneric")
    public String deleteGeneric(@RequestBody Integer id){
        return genericService.deleteGeneric(id);
    }
}
