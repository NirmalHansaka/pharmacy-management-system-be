package com.pms.pharmacy.system.service;

import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Customer;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getCompanies(){
        return companyRepository.findAllByDeleted(false);
    }

    public Company saveCustomer(Company company){
        return companyRepository.save(company);
    }

    public Company updateCompany(Company company){
        Company existingCompany= companyRepository.findById(company.getCompany_id()).orElse(null);
        existingCompany.setCompany_id(company.getCompany_id());
        existingCompany.setCompany_name(company.getCompany_name());
        existingCompany.setContact_number(company.getContact_number());
        existingCompany.setAddress(company.getAddress());
        existingCompany.setEmail(company.getEmail());
        return companyRepository.save(existingCompany);
    }

    public String deleteCompany(int id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()){
            Company company = optionalCompany.get();
            company.setDeleted(true);
            companyRepository.save(company);
            return "Company removed";
        }

        return "Company not exsist";
    }


}
