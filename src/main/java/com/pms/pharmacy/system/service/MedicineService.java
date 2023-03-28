package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.Medicine;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {

    @Autowired
    MedicineRepository medicineRepository;

    public List<Medicine> getMedicines(){
        return medicineRepository.findAllByDeleted(false);
    }

    public Medicine saveMedicine(Medicine medicine){
        return medicineRepository.save(medicine);
    }

    public Medicine updateMedicine(Medicine medicine){
        Medicine existingMedicine = medicineRepository.findById(medicine.getId()).orElse(null);
        existingMedicine.setId(medicine.getId());
        existingMedicine.setName(medicine.getName());
        existingMedicine.setQuantity(medicine.getQuantity());
        existingMedicine.setUnitPrice(medicine.getUnitPrice());
        return medicineRepository.save(existingMedicine);
    }

    public String deleteMedicine(int id){
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        if (optionalMedicine.isPresent()){
            Medicine medicine = optionalMedicine.get();
            medicine.setDeleted(true);
            medicineRepository.save(medicine);
            return "Medicine removed";
        }

        return "Medicine not exsist";
    }


}
