package com.bank.Blood.Bank.service.impl;

import com.bank.Blood.Bank.model.Admin;
import com.bank.Blood.Bank.model.Staff;
import com.bank.Blood.Bank.repository.AdminRepository;
import com.bank.Blood.Bank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }
    @Override
    public Admin findOne(Integer id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.isEmpty() ? null : admin.get();
    }
    @Override
    public Admin update(Admin admin, Integer id){
        Optional<Admin> editAdmin = adminRepository.findById(id);
        editAdmin.get().setIsEnabled(true);
        editAdmin.get().setIsLocked(false);
        adminRepository.save(editAdmin.get());
        return editAdmin.isEmpty()? null : editAdmin.get();
    }
}
