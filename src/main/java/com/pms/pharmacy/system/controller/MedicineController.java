package com.pms.pharmacy.system.controller;


import com.pms.pharmacy.system.model.Medicine;
import com.pms.pharmacy.system.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @GetMapping("/getMedicine")
    public List<Medicine> getMedicines(){return medicineService.getMedicines(); }

    @PostMapping("/addMedicine")
    public Medicine addMedicine(@RequestBody Medicine medicine){
        return  medicineService.saveMedicine(medicine);
    }

    @PutMapping("/updateMedicine")
    public Medicine updateMedicine(@RequestBody Medicine medicine){
        return medicineService.updateMedicine(medicine);
    }

    @DeleteMapping("/deleteMedicine")
    public String deleteMedicine(@RequestBody Integer id){
        return medicineService.deleteMedicine(id);
    }
}
