package com.pms.pharmacy.system.service;


import com.pms.pharmacy.system.model.Generic;
import com.pms.pharmacy.system.model.Role;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenericService {

    @Autowired
    GenericRepository genericRepository;

    public List<Generic> getGeneric(){
        return genericRepository.findAllByDeleted(false);
    }

    public Generic saveGeneric(Generic generic){
        return genericRepository.save(generic);
    }

    public Generic updateGeneric(Generic generic){
        Generic existingGeneric = genericRepository.findById(generic.getGeneric_id()).orElse(null);
        existingGeneric.setGeneric_id(generic.getGeneric_id());
        existingGeneric.setGeneric_name(generic.getGeneric_name());
        return genericRepository.save(generic);
    }

    public String deleteGeneric(int id){
        Optional<Generic> optionalGeneric = genericRepository.findById(id);
        if (optionalGeneric.isPresent()){
            Generic generic = optionalGeneric.get();
            generic.setDeleted(true);
            genericRepository.save(generic);
            return "Generic removed";
        }

        return "Generic not exsist";
    }

}
