package com.bank.Blood.Bank.service;

import com.bank.Blood.Bank.model.AppUser;
import com.bank.Blood.Bank.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService{

    private AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }


    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }
}
