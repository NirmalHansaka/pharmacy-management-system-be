package com.pms.pharmacy.system.controller;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Customer;
import com.pms.pharmacy.system.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @GetMapping("/getCompanies")
    public List<Company> getCompanies(){
        return companyService.getCompanies();
    }

    @PostMapping("/addCompany")
    public Company addCompany(@RequestBody Company company){
        return  companyService.saveCustomer(company);
    }

    @PutMapping("/updateCompany")
    public Company updateCompany(@RequestBody Company company){
        return companyService.updateCompany(company);
    }

    @DeleteMapping("/deleteCompany")
    public String deleteCompany(@RequestBody Integer id){
        return companyService.deleteCompany(id);
    }
}

